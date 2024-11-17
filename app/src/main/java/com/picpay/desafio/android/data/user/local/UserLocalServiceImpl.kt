package com.picpay.desafio.android.data.user.local

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.data.user.db.UserDao
import com.picpay.desafio.android.data.user.db.UserEntity

class UserLocalServiceImpl(private val dao: UserDao) : UserLocalService {
    override fun getUsersLiveData(): LiveData<List<UserEntity>> {
        return dao.getUsersLiveData()
    }

    override suspend fun getUsers(): List<UserEntity> {
        return dao.getUsers()
    }

    override suspend fun saveUsers(users: List<UserEntity>) {
        dao.insertAll(users)
    }

    override suspend fun cleanUsers() {
        dao.deleteAll()
    }
}