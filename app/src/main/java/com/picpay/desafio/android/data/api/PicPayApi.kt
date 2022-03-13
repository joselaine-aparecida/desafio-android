package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.network.response.UserResponse
import retrofit2.http.GET

interface PicPayApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}