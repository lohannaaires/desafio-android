package com.picpay.desafio.android.ui.application

import android.app.Application
import com.picpay.desafio.android.data.common.di.LocalModule
import com.picpay.desafio.android.data.common.di.RemoteModule
import com.picpay.desafio.android.data.user.di.UserModule as UserData
import com.picpay.desafio.android.ui.user.di.UserModule as UserUI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                LocalModule.dbModule,
                RemoteModule.networkModule,
                UserData.localModule,
                UserData.serviceModule,
                UserData.repositoryModule,
                UserUI.viewModelModule
            )
        }
    }
}