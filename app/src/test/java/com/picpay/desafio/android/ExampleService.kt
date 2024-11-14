package com.picpay.desafio.android

import com.picpay.desafio.android.data.user.model.User
import com.picpay.desafio.android.data.user.remote.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}