package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.data.exceptions.GetUsersException
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
class UserRemoteDataSourceTest {
    private lateinit var remoteDataSource: UserDataSource
    private lateinit var api: PicPayApi
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        api = mockk()
        dao = mockk()
        remoteDataSource = UserRemoteDataSource(dao, api)
    }

    @Test
    fun givenInvoke_whenSuccess_shouldReturnListOfUsers() = runTest {
        coEvery {
            api.getUsers()
        } returns createListOfUserResponse()
        coEvery {
            dao.saveList(any())
        } returns Unit

        val expectedResult = createListOfUser()

        assertThat(remoteDataSource(), IsEqual(expectedResult))

        coVerify(exactly = 1) { dao.saveList(any()) }
    }

    @Test(expected = GetUsersException::class)
    fun givenInvoke_whenUnsuccessful_shouldThrowGetUsersException() = runTest {
        coEvery {
            api.getUsers()
        } throws GetUsersException()

        remoteDataSource()

        coVerify(exactly = 1) { dao.saveList(any()) }
    }
}