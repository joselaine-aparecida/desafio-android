package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.domain.model.User

interface UserDataSource {
    suspend operator fun invoke() : List<User>
}