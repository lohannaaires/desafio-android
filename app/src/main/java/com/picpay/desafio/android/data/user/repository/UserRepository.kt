package com.picpay.desafio.android.data.user.repository

import com.picpay.desafio.android.data.common.model.ApiResult
import com.picpay.desafio.android.data.user.model.User

interface UserRepository {
    suspend fun getUsers(): ApiResult<List<User>>
}