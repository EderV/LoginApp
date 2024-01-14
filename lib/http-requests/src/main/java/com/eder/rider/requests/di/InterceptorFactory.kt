package com.eder.rider.requests.di

import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.interceptors.TokenInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterceptorFactory @Inject constructor() {

    fun createTokenInterceptor(
        gson: Gson,
        authPrefs: UserAuthPrefs,
        onForcedLogout: () -> Unit,
    ): Interceptor {
        return TokenInterceptor(gson, authPrefs, onForcedLogout)
    }

}