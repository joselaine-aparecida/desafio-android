package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.domain.models.UserEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.network.response.UserResponse


fun UserResponse.toDomain(): User {
    return User(
        this.id.toInt(),
        this.name,
        this.username,
        this.img
    )
}

fun UserResponse.toEntity(): UserEntity {
    return UserEntity(this.id.toInt(), this.name, this.username, this.img)
}

fun UserEntity.toDomain(): User {
    return User(this.id, this.name, this.username, this.img)
}

