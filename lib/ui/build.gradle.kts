android {
    namespace = "com.eder.rider.ui"
}

dependencies {

    implementation("androidx.core:core-ktx:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.appcompat:appcompat:${rootProject.extra.get("androidxAppcompatVersion")}")
    implementation("com.google.android.material:material:${rootProject.extra.get("androidMaterialVersion")}")

    implementation(project(":lib:common"))
}