package com.picpay.desafio.android.presenter.utils

import com.picpay.desafio.android.domain.model.User

sealed class ViewState{
    data class Success(val users: List<User>) : ViewState()
    object Loading : ViewState()
    object Error : ViewState()
}