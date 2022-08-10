package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.di.CoroutinesDispatchers
import com.picpay.desafio.android.domain.usecases.GetUserUseCase
import com.picpay.desafio.android.presenter.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val dispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun getUsers() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io()) {
            getUserUseCase().onSuccess { users ->
                _viewState.value = ViewState.Success(users)
            }.onFailure {
               _viewState.value = ViewState.Error
            }
        }
    }
}