package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.domain.model.User
import javax.inject.Inject

class UserCacheDataSource @Inject constructor(
    private val dao: UserDao
) : UserDataSource {

    override suspend fun invoke(): List<User> {
        return dao.getList().map {
            it.toDomain()
        }
    }

}