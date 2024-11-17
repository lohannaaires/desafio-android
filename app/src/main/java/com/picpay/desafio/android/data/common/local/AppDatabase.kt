package com.picpay.desafio.android.data.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.user.db.UserDao
import com.picpay.desafio.android.data.user.db.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}