package es.evm.exmpl.common.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.evm.exmpl.common.di.anotations.BasicGson
import es.evm.exmpl.common.di.anotations.LenientGson

@Module
@InstallIn(SingletonComponent::class)
class GsonModule {

    @Provides
    @BasicGson
    fun getBasicGson(): Gson {
        return Gson()
    }

    @Provides
    @LenientGson
    fun getLenientGson(): Gson = GsonBuilder().setLenient().create()

}