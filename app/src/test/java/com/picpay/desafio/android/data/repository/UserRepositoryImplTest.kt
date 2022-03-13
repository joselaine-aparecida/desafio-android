package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.mapper.toEntity
import com.picpay.desafio.android.factory.createListOfUserResponse
import com.picpay.desafio.android.network.exceptions.GetUsersException
import com.picpay.desafio.android.network.exceptions.GetUsersFromNetwork
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {
    private lateinit var service: PicPayApi
    private lateinit var dao: UserDao
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        service = mockk()
        dao = mockk()
        userRepository = UserRepositoryImpl(service, dao)
    }

    @Test
    fun givenGetUsersFromNetwork_whenSuccess_shouldReturnListOfUser(): Unit = runBlocking {
        coEvery {
            service.getUsers()
        } returns createListOfUserResponse()

        coEvery {
            dao.getList()
        } returns createListOfUserResponse().map {
            it.toEntity()
        }

        val result = userRepository.getUsers()
        val expectedResult = createListOfUserResponse().map {
            it.toDomain()
        }

        assertThat(result, IsEqual(expectedResult))
        coVerify(exactly = 1) { service.getUsers() }
    }

    @Test(expected = GetUsersException::class)
    fun givenGetUsers_whenUnsuccessful_shouldThrowGetUsersException(): Unit = runBlocking {
        coEvery {
            service.getUsers()
        } throws GetUsersFromNetwork()
        coEvery {
            dao.getList()
        } throws GetUsersException()

        userRepository.getUsers()
    }
}