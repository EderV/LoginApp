package com.eder.rider.requests

import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDefault @Inject constructor() : BaseRetrofit() {

    override val baseUrl: String = RetrofitHelper.getBaseUrl()

    // TODO Add TokenInterceptor to OkHttpClient

}