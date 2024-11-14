package com.picpay.desafio.android.data.user.repository

import android.util.Log
import com.picpay.desafio.android.data.common.model.ApiResult
import com.picpay.desafio.android.data.user.model.User
import com.picpay.desafio.android.data.user.remote.PicPayService

class UserRepositoryImpl(private val service: PicPayService) : UserRepository {
    override suspend fun getUsers(): ApiResult<List<User>> {
        return try {
            val callback = service.getUsers()
            callback.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error(Throwable(callback.message()))

        } catch (e: Throwable) {
            e.printStackTrace()
            Log.e("UserRepositoryImpl", e.message.toString())
            ApiResult.Error(Throwable("Ocorreu um erro. Tente novamente."))
        }
    }
}