package es.evm.exmpl.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.evm.exmpl.common.logger.Logger
import es.evm.exmpl.common.logger.LoggerDefault
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun getDefaultLogger(logger: LoggerDefault): Logger

}