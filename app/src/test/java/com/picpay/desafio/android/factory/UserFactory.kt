package com.picpay.desafio.android.factory

import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.model.UserResponse

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

fun createListOfUserEntity(): List<UserEntity> {
    return listOf(
        UserEntity(
            id = 1,
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