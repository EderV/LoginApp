package com.eder.rider.requests.di

import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.BaseRetrofit
import com.eder.rider.requests.RetrofitDefault
import com.eder.rider.requests.RetrofitService
import com.eder.rider.requests.apis.AuthService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RequestsModule {

    @Provides
    fun provideRetrofitService(
        interceptorFactory: InterceptorFactory,
        authPrefs: UserAuthPrefs
    ): RetrofitService {
        return RetrofitDefault(interceptorFactory, authPrefs)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofitService: RetrofitService): AuthService {
        return retrofitService.createRetrofitService(AuthService::class.java)
    }

}