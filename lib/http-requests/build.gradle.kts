android {
    namespace = "com.eder.rider.requests"
}

dependencies {

//    implementation("androidx.core:core-ktx:1.12.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.11.0")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra.get("retrofitVersion")}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra.get("retrofitVersion")}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${rootProject.extra.get("retrofitVersion")}")

    implementation("com.squareup.okhttp3:okhttp:${rootProject.extra.get("okhttpVersion")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra.get("okhttpVersion")}")

    implementation("io.reactivex.rxjava2:rxjava:${rootProject.extra.get("rxJavaVersion")}")
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra.get("rxAndroidVersion")}")

    implementation(project(":lib:preferences"))
    implementation(project(":lib:common"))

}