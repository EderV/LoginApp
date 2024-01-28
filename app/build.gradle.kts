android {
    namespace = "com.eder.rider.loginapp"
}

dependencies {

    implementation("androidx.core:core-ktx:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.appcompat:appcompat:${rootProject.extra.get("androidxAppcompatVersion")}")
    implementation("com.google.android.material:material:${rootProject.extra.get("androidMaterialVersion")}")
    implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra.get("androidxConstraintVersion")}")

    implementation("io.reactivex.rxjava2:rxjava:${rootProject.extra.get("rxJavaVersion")}")
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra.get("rxAndroidVersion")}")

    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra.get("jUnitExtVersion")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra.get("espressoCoreVersion")}")
    

    implementation(project(":feat:main-activity"))

    implementation(project(":lib:http-requests"))
    implementation(project(":lib:preferences"))
    implementation(project(":lib:common"))
    implementation(project(":lib:ui"))

}