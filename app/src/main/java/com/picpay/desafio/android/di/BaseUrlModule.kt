package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = BuildConfig.BASE_URL
}