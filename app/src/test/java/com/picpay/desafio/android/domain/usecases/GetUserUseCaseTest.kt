package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.factory.createListOfUser
import com.picpay.desafio.android.network.exceptions.GetUsersException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test


class GetUserUseCaseTest{
    private lateinit var userRepository: com.picpay.desafio.android.data.repository.UserRepository
    private lateinit var useCase: GetUserUseCase

    @Before
    fun setUp() {
        userRepository = mockk()
        useCase = GetUserUseCase(userRepository)
    }

    @Test
    fun givenGetUsers_whenSuccess_shouldReturnSuccessResult(): Unit = runBlocking {
        coEvery {
            userRepository.getUsers()
        } returns createListOfUser()

        val result = useCase.invoke()
        val expectedResult = Result.success(createListOfUser())

        assertThat(result, IsEqual(expectedResult))
        coVerify(exactly = 1) { userRepository.getUsers() }
    }

    @Test
    fun givenGetUsers_whenUnsuccessful_shouldReturnFailedResult(): Unit = runBlocking {
        coEvery {
            userRepository.getUsers()
        } throws GetUsersException()

        val result = useCase.invoke()
        val expectedResult = Result.failure<GetUsersException>(GetUsersException())

        assertThat(result, IsEqual(expectedResult))
        coVerify(exactly = 1) { userRepository.getUsers() }
    }

}