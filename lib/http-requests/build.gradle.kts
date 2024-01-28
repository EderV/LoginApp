android {
    namespace = "com.eder.rider.requests"
}

dependencies {

    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra.get("retrofitVersion")}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra.get("retrofitVersion")}")
    implementation("com.squareup.retrofit2:converter-scalars:${rootProject.extra.get("retrofitVersion")}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${rootProject.extra.get("retrofitVersion")}")

    implementation("com.squareup.okhttp3:okhttp:${rootProject.extra.get("okhttpVersion")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra.get("okhttpVersion")}")

    implementation("io.reactivex.rxjava2:rxjava:${rootProject.extra.get("rxJavaVersion")}")
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra.get("rxAndroidVersion")}")

    implementation(project(":lib:preferences"))
    implementation(project(":lib:logout"))
    implementation(project(":lib:common"))

}