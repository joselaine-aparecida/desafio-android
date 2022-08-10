package com.picpay.desafio.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao

    companion object{
        const val USER_TABLE_NAME = "users"
    }
}