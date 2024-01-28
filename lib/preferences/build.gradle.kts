android {
    namespace = "com.eder.rider.preferences"
}

dependencies {

    implementation("androidx.core:core-ktx:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    implementation(project(":lib:common"))

}