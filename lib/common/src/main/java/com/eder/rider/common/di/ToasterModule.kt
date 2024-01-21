package com.eder.rider.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.common.toaster.ToasterDefault
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ToasterModule {

    @Binds
    @Singleton
    abstract fun getDefaultToaster(toaster: ToasterDefault): Toaster

}