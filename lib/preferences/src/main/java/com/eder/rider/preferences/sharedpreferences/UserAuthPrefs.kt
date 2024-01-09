package com.eder.rider.preferences.sharedpreferences

import com.eder.rider.entities.UserAuth

interface UserAuthPrefs {

    fun saveUserAuth(userAuth: UserAuth)

    fun getUserAuth(): UserAuth

    fun getUserId(): String

    fun getAuthToken(): String

    fun getRefreshToken(): String

}