package com.eder.rider.requests

import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.di.InterceptorFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDefault @Inject constructor(
    private val interceptorFactory: InterceptorFactory,
    private val authPrefs: UserAuthPrefs
) : BaseRetrofit() {

    override val baseUrl: String = RetrofitHelper.getBaseUrl()

    override val okHttpClient: OkHttpClient.Builder
        get() = super.okHttpClient
            .addInterceptor(interceptorFactory.createTokenInterceptor(Gson(), authPrefs) {
                // TODO: Do forced logout
            })

}