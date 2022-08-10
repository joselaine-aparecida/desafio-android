package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return repository.getUsers().fold(
            onSuccess = { users ->
               Result.success(users)
            },
            onFailure = { throwble ->
                Result.failure(throwble)
            }
        )
    }
}