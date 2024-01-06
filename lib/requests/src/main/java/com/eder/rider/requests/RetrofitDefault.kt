package com.eder.rider.requests

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDefault @Inject constructor() : BaseRetrofit() {

    override val baseUrl: String = "http://192.168.1.200:8080"

}