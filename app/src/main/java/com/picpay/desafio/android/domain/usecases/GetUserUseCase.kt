package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.models.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return try {
            Result.success(repository.getUsers())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}