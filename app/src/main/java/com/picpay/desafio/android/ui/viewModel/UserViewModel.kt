package com.picpay.desafio.android.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.common.model.ApiResult
import com.picpay.desafio.android.data.user.model.User
import com.picpay.desafio.android.data.user.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _usersData = MutableLiveData<List<User>>()
    val usersData: LiveData<List<User>>
        get() = _usersData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getUsers() {
        viewModelScope.launch {
            when (val response = repository.getUsers()) {
                is ApiResult.Success -> {
                    _usersData.postValue(response.data)
                }

                is ApiResult.Error -> {
                    _error.postValue(response.exception.message)
                }
            }
        }
    }
}