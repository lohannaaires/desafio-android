package com.picpay.desafio.android.ui.user.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.user.db.UserEntity
import com.picpay.desafio.android.data.user.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users: LiveData<List<UserEntity>> = repository.getCachedUsersLiveData()
    val users: LiveData<List<UserEntity>>
        get() = _users

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadUsers() {
        viewModelScope.launch {
            try {
                repository.fetchAndCacheUsers()

                val cachedUsers = repository.getCachedUsers()

                _error.value = when {
                    cachedUsers.isEmpty() -> getErrorMessage()
                    else -> null
                }
            } catch (e: Exception) {
                _error.value = getErrorMessage()
            }
        }
    }

    fun cleanErrorMessage() {
        _error.value = null
    }

    private fun getErrorMessage(): String {
        return "Erro ao carregar os dados. Tente novamente mais tarde."
    }
}