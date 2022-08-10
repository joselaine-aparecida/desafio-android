package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasources.UserCacheDataSource
import com.picpay.desafio.android.data.datasources.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserCacheDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val usersRemote = remoteDataSource()
            Result.success(usersRemote)
        } catch (e: Exception) {
            val usersDb = localDataSource()
            if (usersDb.isEmpty()) {
                Result.failure(e)
            } else {
                Result.success(usersDb)
            }
        }
    }

}