package com.picpay.desafio.android.factory

import com.picpay.desafio.android.domain.models.UserEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.network.response.UserResponse

fun createListOfUser(): List<User> {
    return listOf(
        User(
            id = "1".toInt(),
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        )
    )
}

fun createListOfUserResponse(): List<UserResponse> {
    return listOf(
        UserResponse(
            id = "1",
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        )
    )
}

 fun createUserResponse(): UserResponse {
    return UserResponse(
        id = "1",
        img = "www.google.com/users",
        name = "Joselaine Aparecida",
        username = "joselaine.ap"
    )
}