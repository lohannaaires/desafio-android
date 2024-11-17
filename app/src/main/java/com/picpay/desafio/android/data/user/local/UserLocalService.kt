package com.picpay.desafio.android.data.user.local

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.data.user.db.UserEntity

interface UserLocalService {
    fun getUsersLiveData(): LiveData<List<UserEntity>>
    suspend fun getUsers(): List<UserEntity>
    suspend fun saveUsers(users: List<UserEntity>)
    suspend fun cleanUsers()
}