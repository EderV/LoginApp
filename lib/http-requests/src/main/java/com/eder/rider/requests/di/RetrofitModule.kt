package com.eder.rider.requests.di

import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.retrofit_impl.RetrofitAuth
import com.eder.rider.requests.retrofit_impl.RetrofitDefault
import com.eder.rider.requests.RetrofitService
import com.eder.rider.requests.di.anotations.AuthRetrofit
import com.eder.rider.requests.di.anotations.DefaultRetrofit
import com.eder.rider.requests.services.AuthService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.evm.exmpl.common.di.anotations.BasicGson
import es.evm.exmpl.common.di.anotations.LenientGson
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @DefaultRetrofit
    fun provideDefaultRetrofitService(
        @LenientGson lenientGson: Gson,
        interceptorFactory: InterceptorFactory,
        authPrefs: UserAuthPrefs,
        @BasicGson gson: Gson
    ): RetrofitService {
        return RetrofitDefault(gson, interceptorFactory, authPrefs, lenientGson)
    }

    @Provides
    @AuthRetrofit
    fun provideAuthRetrofitService(
        @LenientGson lenientGson: Gson
    ): RetrofitService {
        return RetrofitAuth(lenientGson)
    }

    @Provides
    @Singleton
    fun provideAuthService(@AuthRetrofit retrofitService: RetrofitService): AuthService {
        return retrofitService.createRetrofitService(AuthService::class.java)
    }

}