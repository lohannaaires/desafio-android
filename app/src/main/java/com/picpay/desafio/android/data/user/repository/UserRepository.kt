package com.picpay.desafio.android.data.user.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.data.user.db.UserEntity

interface UserRepository {
    fun getCachedUsersLiveData(): LiveData<List<UserEntity>>
    suspend fun getCachedUsers(): List<UserEntity>
    suspend fun fetchAndCacheUsers()
}