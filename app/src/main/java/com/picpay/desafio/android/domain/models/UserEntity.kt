package com.picpay.desafio.android.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val img: String
) {

    companion object {
        const val TABLE_NAME = "users"
    }
}