package com.eder.rider.common.di.anotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicGson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LenientGson

