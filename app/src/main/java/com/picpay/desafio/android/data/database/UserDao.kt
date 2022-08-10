package com.picpay.desafio.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.picpay.desafio.android.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun saveList(list: List<UserEntity>)
    @Query("SELECT * FROM users")
    fun getList(): List<UserEntity>
}