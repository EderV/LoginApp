package com.eder.rider.requests.apis

import io.reactivex.Flowable
import retrofit2.http.GET

interface AuthService {

    @GET("/api/auth/test")
    fun test(): Flowable<String>

}