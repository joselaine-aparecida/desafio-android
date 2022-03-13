package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.domain.models.UserEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.network.exceptions.GetUsersException
import com.picpay.desafio.android.network.exceptions.GetUsersFromNetwork
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: PicPayApi,
    private val dao: UserDao
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            getUsersFromNetwork()
        } catch (e: GetUsersFromNetwork) {
            getUserFromDb()
        } catch (e: Exception) {
            throw GetUsersException()
        }
    }

    private suspend fun getUsersFromNetwork(): List<User> {
        return try {
            service.getUsers().map {
                saveListOfUsers(listOf(it.toEntity()))
                it.toDomain()
            }
        } catch (e: Exception) {
            throw GetUsersFromNetwork()
        }
    }

    private fun getUserFromDb(): List<User> {
        return if (dao.getList().isNotEmpty()){
            dao.getList().map {
                it.toDomain()
            }
        } else {
            throw GetUsersException()
        }
    }

    private fun saveListOfUsers(newList: List<UserEntity>) {
        dao.saveList(newList)
    }
}