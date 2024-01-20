package com.eder.rider.preferences.sharedpreferences

interface UserAuthPrefs {

    fun saveUserAuth(userAuth: es.evm.exmpl.common.model.UserAuth)

    fun getUserAuth(): es.evm.exmpl.common.model.UserAuth

    fun getUserId(): String

    fun getAuthToken(): String

    fun getRefreshToken(): String

}