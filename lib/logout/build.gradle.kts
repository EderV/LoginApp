android {
    namespace = "com.eder.rider.logout"
}

dependencies {

    implementation("androidx.core:core-ktx:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.appcompat:appcompat:${rootProject.extra.get("androidxAppcompatVersion")}")

    implementation(project(":lib:preferences"))
    implementation(project(":lib:ui"))
    implementation(project(":lib:common"))

}