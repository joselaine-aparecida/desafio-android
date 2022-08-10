package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {
    @Binds
    fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository
}