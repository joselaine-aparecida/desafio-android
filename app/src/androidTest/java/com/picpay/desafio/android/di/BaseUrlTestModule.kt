package com.picpay.desafio.android.di

import com.picpay.desafio.android.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = "http://localhost:8080/"

}