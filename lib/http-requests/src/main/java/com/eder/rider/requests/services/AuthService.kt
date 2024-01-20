package com.eder.rider.requests.services

import com.eder.rider.requests.model.UserLogin
import es.evm.exmpl.common.model.UserAuth
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("/api/auth/login")
    fun login(
        @Body body: UserLogin
    ): Flowable<UserAuth>

    @GET("/api/test")
    fun test(): Flowable<String>

}