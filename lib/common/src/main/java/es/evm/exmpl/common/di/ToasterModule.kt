package es.evm.exmpl.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.evm.exmpl.common.toaster.Toaster
import es.evm.exmpl.common.toaster.ToasterDefault
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ToasterModule {

    @Binds
    @Singleton
    abstract fun getDefaultToaster(toaster: ToasterDefault): Toaster

}