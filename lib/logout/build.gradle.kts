android {
    namespace = "com.eder.rider.logout"
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    implementation(project(":lib:preferences"))
    implementation(project(":lib:ui"))
    implementation(project(":lib:common"))

}