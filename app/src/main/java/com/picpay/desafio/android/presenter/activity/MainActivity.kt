package com.picpay.desafio.android.presenter.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presenter.viewmodel.MainViewModel
import com.picpay.desafio.android.presenter.adapter.UserListAdapter
import com.picpay.desafio.android.presenter.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userAdapter = UserListAdapter()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
        setUpObservables()
        setLoadingState()
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                setViewState(state)
            }
        }
    }

    private fun initRecyclerView() {
        viewModel.getUsers()
        binding.recyclerView.adapter = userAdapter
    }

    private fun setViewState(state: ViewState) {
        when (state) {
            is ViewState.Loading -> setLoadingState()
            is ViewState.Success -> setContentState(users = state.users)
            is ViewState.Error -> setErrorState()
        }
    }

    private fun setLoadingState() {
        with(binding) {
            userListProgressBar.isVisible = true
            recyclerView.isVisible = false
        }
    }

    private fun setContentState(users: List<User>) {
        with(binding) {
            userListProgressBar.isVisible = false
            recyclerView.isVisible = true
        }
        userAdapter.submitList(users)
    }

    private fun setErrorState() {
        with(binding) {
            userListProgressBar.isVisible = false
            recyclerView.isVisible = false
        }
        Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_SHORT).show()
    }

}
