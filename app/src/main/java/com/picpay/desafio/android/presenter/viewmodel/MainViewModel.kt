package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.usecases.GetUserUseCase
import com.picpay.desafio.android.presenter.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>>
        get() = _users

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getUsers() {
        _viewState.postValue(ViewState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().onSuccess { users ->
                _users.postValue(users)
                _viewState.postValue(ViewState.CONTENT)
            }.onFailure {
               _viewState.postValue(ViewState.ERROR)
            }
        }
    }
}