package com.picpay.desafio.android.ui.user.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.user.db.UserEntity
import com.picpay.desafio.android.data.user.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository, application: Application) :
    AndroidViewModel(application) {
    private val _users: LiveData<List<UserEntity>> = repository.getCachedUsersLiveData()
    val users: LiveData<List<UserEntity>>
        get() = _users

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val resources = application.resources

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.fetchAndCacheUsers()

                val cachedUsers = repository.getCachedUsers()

                when {
                    cachedUsers.isEmpty() -> _error.postValue(getErrorMessage())
                    else -> _error.postValue(null)
                }
            } catch (e: Exception) {
                _error.postValue(getErrorMessage())
            }
        }
    }

    fun cleanErrorMessage() {
        _error.postValue(null)
    }

    private fun getErrorMessage(): String {
        return resources.getString(R.string.error)
    }
}