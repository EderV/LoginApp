Android basic project which implements user registration and login using Retrofit and RxJava. The project is highly modularized and ready to grow.

## How to run ▶️
* Backend
  * You can use as backend my other project [JwtAuthSpringBoot3](https://github.com/EderV/JwtAuthSpringBoot3)
* App 
  * With Android Studio
    * Download and install Android Studio version compatible with Gradle and AGP version >8
    * Run with Android Studio run button
  * Without Android Studio
    * Run `./gradlew assembleRelease` command in the root of the project
    * Connect your device
    * Locate the output file `<project>/app/build/outputs/apk/release/app-release.apk`
    * Run `adb install app-release.apk` command

## Features 🔬
# General
* Dependency injection with hilt
* Clean code
* Modularized
* Project gradle file has the common configuration of all subprojects
# User
* Login of an exiting user
  * Checks empty fields
  * Checks illegal characters
* Registration of new users
  * Checks empty fields
  * Checks illegal characters
  * Checks if email has valid format
* Stores access token and refresh token for the session
* Refreshes access token with refresh token when the session is expired
* Force logout user when both tokens are expired