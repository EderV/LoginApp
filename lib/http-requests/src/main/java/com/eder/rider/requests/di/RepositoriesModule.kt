package com.eder.rider.requests.di

import com.eder.rider.requests.repositories.AuthRepository
import com.eder.rider.requests.repositories.AuthRepositoryDefault
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun getAuthRepository(authRepo: AuthRepositoryDefault): AuthRepository

}