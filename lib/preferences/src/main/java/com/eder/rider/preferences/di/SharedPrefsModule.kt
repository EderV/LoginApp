package com.eder.rider.preferences.di

import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefsDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPrefsModule {

    @Binds
    @Singleton
    abstract fun bindUserAuthPrefs(userAuthPrefs: UserAuthPrefsDefault): UserAuthPrefs

}