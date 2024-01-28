package com.eder.rider.requests.retrofit_impl

import com.eder.rider.logout.LogoutManager
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.BaseRetrofit
import com.eder.rider.requests.RetrofitHelper
import com.eder.rider.requests.di.InterceptorFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient

class RetrofitDefault (
    lenientGson: Gson,
    private val interceptorFactory: InterceptorFactory,
    private val authPrefs: UserAuthPrefs,
    private val gson: Gson,
    private val logoutManager: LogoutManager,
) : BaseRetrofit(lenientGson) {

    override val baseUrl: String = RetrofitHelper.getBaseUrl()

    override val okHttpClientBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder()
            .addInterceptor(interceptorFactory.createTokenInterceptor(gson, authPrefs) {
                this.onForceLogout()
            })

    private fun onForceLogout() {
        super.okHttpClient.dispatcher.cancelAll()
        logoutManager.forceLogout()
    }

}