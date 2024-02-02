import com.android.build.gradle.BaseExtension

val kotlinVersion by rootProject.extra { "1.12.0" }
val daggerHiltVersion by rootProject.extra { "2.49" }
val gsonVersion by rootProject.extra { "2.10.1" }

val retrofitVersion by rootProject.extra { "2.9.0" }
val okhttpVersion by rootProject.extra { "4.10.0" }

val rxJavaVersion by rootProject.extra { "2.2.19" }
val rxAndroidVersion by rootProject.extra { "2.1.1" }

val androidMaterialVersion by rootProject.extra { "1.11.0" }
val androidxAppcompatVersion by rootProject.extra { "1.6.1" }
val androidxConstraintVersion by rootProject.extra { "2.1.4" }

val jUnitVersion by rootProject.extra { "4.13.2" }
val jUnitExtVersion by rootProject.extra { "1.1.5" }
val espressoCoreVersion by rootProject.extra { "3.5.1" }

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
    }
}

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    /*
        If there are modules inside a folder, do not apply plugins to parent folder

        Example:
            - LoginApp
            -- app
            -- lib
            ---- api
            ---- files

        "lib" will be considered as subproject, but we are using it as a folder for other subprojects.
        Do not apply any plugin to that "subproject" to avoid gradle problems
   */

    val excludedSubprojects = arrayListOf("lib", "feat")

    if (this@subprojects.name == "app") {
        apply(plugin = "com.android.application")
    }
    else if (excludedSubprojects.none { it == this@subprojects.name }) {
        apply(plugin = "com.android.library")
    }

    if (excludedSubprojects.none { it == this@subprojects.name }) {
        apply(plugin = "org.jetbrains.kotlin.android")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "com.google.dagger.hilt.android")

        // https://stackoverflow.com/questions/57627119/android-subprojects-with-kotlin-dsl?rq=3
        plugins.withType<BasePlugin> {
            configure<BaseExtension> {

                defaultConfig {
                    if (this@subprojects.name == "app") applicationId = "com.eder.rider.androidhilt"
                    minSdk = 26
                    targetSdk = 34
                    compileSdkVersion(34)
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )

                        buildConfigField("String", "BASE_URL", "\"http://192.168.1.200:8080\"")
                    }
                    getByName("debug") {
                        isDebuggable = true

//                        buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080\"")
                        buildConfigField("String", "BASE_URL", "\"http://192.168.1.185:8080\"")
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                buildFeatures.buildConfig = true
                buildFeatures.viewBinding = true

                dataBinding.enable = true

            }
        }

        dependencies {
            add("implementation", "com.google.code.gson:gson:${rootProject.extra.get("gsonVersion")}")

            add("implementation", "com.google.dagger:hilt-android:${rootProject.extra.get("daggerHiltVersion")}")
            add("kapt", "com.google.dagger:hilt-android-compiler:${rootProject.extra.get("daggerHiltVersion")}")

            add("testImplementation", "junit:junit:${rootProject.extra.get("jUnitVersion")}")
        }
    }

}