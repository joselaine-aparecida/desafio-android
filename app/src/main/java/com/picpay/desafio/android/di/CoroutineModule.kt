package com.picpay.desafio.android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineModule {
    @Binds
        fun bindDispatchers(dispatcher: AppCoroutinesDispatchers): CoroutinesDispatchers
}