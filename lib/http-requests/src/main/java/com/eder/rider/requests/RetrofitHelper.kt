package com.eder.rider.requests

object RetrofitHelper {

    fun getBaseUrl() = BuildConfig.BASE_URL

    fun getAuthEndpoint() = "${BuildConfig.BASE_URL}/api/auth/token"

}