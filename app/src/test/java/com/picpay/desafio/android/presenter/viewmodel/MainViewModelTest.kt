package com.picpay.desafio.android.presenter.viewmodel

import MainCoroutineRule
import com.picpay.desafio.android.data.exceptions.GetUsersException
import com.picpay.desafio.android.domain.usecases.GetUserUseCase
import com.picpay.desafio.android.factory.createListOfUser
import com.picpay.desafio.android.presenter.utils.ViewState.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var useCase: GetUserUseCase

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = MainViewModel(useCase, mainCoroutineRule.testDispatcherProvider)
    }

    @Test
    fun givenGetUsers_whenSuccess_shouldChangeStateToSuccessWithListOfUsers() = runTest {
        coEvery {
            useCase()
        } returns Result.success(createListOfUser())

        viewModel.getUsers()

        val expectedState = Success(createListOfUser())

        assertThat(viewModel.viewState.value, IsEqual(expectedState))
    }

    @Test
    fun givenCreateViewModel_whenInitialState_shouldChangeStateToLoading() = runTest {
        coEvery {
            useCase()
        } returns Result.success(createListOfUser())

        val expectedState = Loading

        assertThat(viewModel.viewState.value, IsEqual(expectedState))
    }

    @Test
    fun givenGetUsers_whenError_shouldChangeStateToError() = runBlocking {
        coEvery {
            useCase()
        } returns Result.failure(GetUsersException())

        viewModel.getUsers()

        val expectedState = Error

        assertThat(viewModel.viewState.value, IsEqual(expectedState))
    }

}