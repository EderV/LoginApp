package com.eder.rider.requests.di

import com.eder.rider.requests.RetrofitService
import com.eder.rider.requests.apis.AuthService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RequestsModule {

    @Provides
    fun bindAuthService(retrofitService: RetrofitService): AuthService {
        return retrofitService.createRetrofitService(AuthService::class.java)
    }

}