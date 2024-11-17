package com.picpay.desafio.android.data.user.model

import com.picpay.desafio.android.data.user.db.UserEntity

object UserMap {
    fun fromDBList(usersEntity: List<UserEntity>): List<UserModel> = usersEntity.map {
        UserModel(
            id = it.id, name = it.name, img = it.img, username = it.username
        )
    }

    fun toDBList(usersModel: List<UserModel>): List<UserEntity> = usersModel.map {
        UserEntity(
            id = it.id, name = it.name, img = it.img, username = it.username
        )
    }
}