package com.picpay.desafio.android.data.common.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.common.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocalModule {
    private fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user-database"
        ).build()
    }

    val dbModule = module {
        single { provideDatabase(androidContext()) }
    }
}