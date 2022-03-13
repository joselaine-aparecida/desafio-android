package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.models.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}