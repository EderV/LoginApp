package com.eder.rider.requests.retrofit_impl

import com.eder.rider.requests.BaseRetrofit
import com.eder.rider.requests.RetrofitHelper
import com.google.gson.Gson
import okhttp3.OkHttpClient

class RetrofitAuth(lenientGson: Gson): BaseRetrofit(lenientGson) {

    override val baseUrl = RetrofitHelper.getBaseUrl()

    override val okHttpClientBuilder = OkHttpClient.Builder()

}