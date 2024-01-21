package com.eder.rider.requests.services

import io.reactivex.Flowable
import retrofit2.http.GET

interface TestService {

    @GET("/api/test")
    fun callTestEndpoint(): Flowable<String>

}