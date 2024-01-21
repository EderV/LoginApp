package com.eder.rider.preferences.sharedpreferences

import com.eder.rider.common.model.UserAuth

interface UserAuthPrefs {

    fun saveUserAuth(userAuth: UserAuth)

    fun getUserAuth(): UserAuth

    fun getUserId(): String

    fun getAccessToken(): String

    fun getRefreshToken(): String

}