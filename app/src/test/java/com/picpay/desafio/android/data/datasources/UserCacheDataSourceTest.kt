package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.database.UserDao
import com.picpay.desafio.android.factory.createListOfUser
import com.picpay.desafio.android.factory.createListOfUserEntity
import com.picpay.desafio.android.data.exceptions.GetUsersException
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
class UserCacheDataSourceTest {
    private lateinit var dataSource: UserDataSource
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        dao = mockk()
        dataSource = UserCacheDataSource(dao)
    }

    @Test
    fun givenInvoke_whenSuccess_shouldReturnListOfUsers() = runTest {
        coEvery {
            dao.getList()
        } returns createListOfUserEntity()

        val expectedResult = createListOfUser()

        assertThat(dataSource(), IsEqual(expectedResult))
    }

    @Test(expected = GetUsersException::class)
    fun givenInvoke_whenUnsuccessful_shouldThrowGetUsersException() = runTest {
        coEvery {
            dao.getList()
        } throws GetUsersException()

        dataSource.invoke()

        coVerify(exactly = 1) { dao.getList() }
    }
}