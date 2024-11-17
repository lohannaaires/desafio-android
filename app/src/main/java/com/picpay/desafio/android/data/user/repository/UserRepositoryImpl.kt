package com.picpay.desafio.android.data.user.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.picpay.desafio.android.data.user.db.UserEntity
import com.picpay.desafio.android.data.user.local.UserLocalService
import com.picpay.desafio.android.data.user.model.UserMap
import com.picpay.desafio.android.data.user.model.UserModel
import com.picpay.desafio.android.data.user.remote.PicPayService

class UserRepositoryImpl(
    private val remoteService: PicPayService,
    private val localService: UserLocalService
) : UserRepository {
    override fun getCachedUsersLiveData(): LiveData<List<UserEntity>> {
        return localService.getUsersLiveData()
    }

    override suspend fun getCachedUsers(): List<UserEntity> {
        return localService.getUsers()
    }

    override suspend fun fetchAndCacheUsers() {
        try {
            val cachedUsers = localService.getUsers()
            val response = remoteService.getUsers()

            if (response.isSuccessful) {
                val usersFromApi = response.body().orEmpty()

                if (shouldUpdateCache(UserMap.fromDBList(cachedUsers), usersFromApi)) {
                    localService.cleanUsers()
                    localService.saveUsers(UserMap.toDBList(usersFromApi))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserRepositoryImpl", e.message.toString())
        }
    }

    private fun shouldUpdateCache(
        cachedUsers: List<UserModel>,
        apiUsers: List<UserModel>
    ): Boolean {
        return cachedUsers.isEmpty() || cachedUsers != apiUsers
    }
}