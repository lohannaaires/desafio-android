package com.picpay.desafio.android.data.user.remote

import com.picpay.desafio.android.data.user.model.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {
    @GET(UserEndPoints.USERS)
    suspend fun getUsers(): Response<List<User>>
}