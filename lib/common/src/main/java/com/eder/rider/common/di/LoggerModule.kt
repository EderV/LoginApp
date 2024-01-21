package com.eder.rider.common.di

import com.eder.rider.common.logger.Logger
import com.eder.rider.common.logger.LoggerDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun getDefaultLogger(logger: LoggerDefault): Logger

}