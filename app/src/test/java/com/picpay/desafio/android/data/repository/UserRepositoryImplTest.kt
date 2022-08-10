package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasources.UserCacheDataSource
import com.picpay.desafio.android.data.datasources.UserRemoteDataSource
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.factory.createListOfUser
import com.picpay.desafio.android.factory.createListOfUserResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {
    private lateinit var remoteDataSource: UserRemoteDataSource
    private lateinit var localDataSource: UserCacheDataSource
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        userRepository = UserRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun givenGetUsers_whenSuccess_shouldReturnListOfUser(): Unit = runTest {
        coEvery {
            remoteDataSource()
        } returns createListOfUser()

        val result = userRepository.getUsers()
        val expectedResult = Result.success(
            createListOfUserResponse().map {
                it.toDomain()
            }
        )

        assertThat(result, IsEqual(expectedResult))

        coVerify(exactly = 1) {  remoteDataSource() }
    }

    @Test
    fun givenGetUsers_whenFailureNetwork_shouldCallLocalDataSource(): Unit = runTest {
        coEvery {
            remoteDataSource()
        } throws Exception()

        coEvery {
            localDataSource()
        } returns createListOfUserResponse().map {
            it.toDomain()
        }

        val result = userRepository.getUsers()
        val expectedResult = Result.success(
            createListOfUserResponse().map {
                it.toDomain()
            }
        )

        assertThat(result, IsEqual(expectedResult))
        coVerify(exactly = 1) { localDataSource() }
    }
}