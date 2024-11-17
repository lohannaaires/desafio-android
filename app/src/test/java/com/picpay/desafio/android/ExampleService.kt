package com.picpay.desafio.android

import com.picpay.desafio.android.data.user.model.UserModel
import com.picpay.desafio.android.data.user.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserModel> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}