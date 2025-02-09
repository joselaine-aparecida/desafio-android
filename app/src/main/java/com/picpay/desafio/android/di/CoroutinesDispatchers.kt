package com.picpay.desafio.android.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface CoroutinesDispatchers {
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class AppCoroutinesDispatchers @Inject constructor(): CoroutinesDispatchers
