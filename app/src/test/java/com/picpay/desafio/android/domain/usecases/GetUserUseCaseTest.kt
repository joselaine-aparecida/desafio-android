package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.factory.createListOfUser
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
class GetUserUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var useCase: GetUserUseCase

    @Before
    fun setUp() {
        userRepository = mockk()
        useCase = GetUserUseCase(userRepository)
    }

    @Test
    fun givenGetUsers_whenSuccess_shouldReturnSuccessResult(): Unit = runTest {
        coEvery {
            userRepository.getUsers()
        } returns Result.success(createListOfUser())

        val result = useCase()
        val expectedResult = Result.success(createListOfUser())

        assertThat(result, IsEqual(expectedResult))
        coVerify(exactly = 1) { userRepository.getUsers() }
    }

    @Test(expected = GetUsersException::class)
    fun givenGetUsers_whenUnsuccessful_shouldReturnFailedResult(): Unit = runTest {
        coEvery {
            userRepository.getUsers()
        } throws GetUsersException()

        useCase()

        coVerify(exactly = 1) { userRepository.getUsers() }
    }

}