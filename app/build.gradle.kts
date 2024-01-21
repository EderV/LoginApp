plugins {
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.eder.rider.loginapp"
}

dependencies {

    implementation("androidx.core:core-ktx:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.android.material:material:${rootProject.extra.get("androidMaterialVersion")}")

    implementation("io.reactivex.rxjava2:rxjava:${rootProject.extra.get("rxJavaVersion")}")
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra.get("rxAndroidVersion")}")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    

    implementation(project(":feat:main-activity"))

    implementation(project(":lib:http-requests"))
    implementation(project(":lib:preferences"))
    implementation(project(":lib:common"))
    implementation(project(":lib:ui"))

}