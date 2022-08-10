package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.model.UserResponse


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

