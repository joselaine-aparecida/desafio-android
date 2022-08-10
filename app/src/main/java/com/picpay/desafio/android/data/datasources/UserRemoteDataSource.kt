package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.domain.model.User
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val dao: UserDao,
    private val service: PicPayApi,
) : UserDataSource {

    override suspend fun invoke(): List<User> {
        return service.getUsers().map {
            saveListOfUsers(listOf(it.toEntity()))
            it.toDomain()
        }
    }

    private fun saveListOfUsers(newList: List<UserEntity>) {
        dao.saveList(newList)
    }

}