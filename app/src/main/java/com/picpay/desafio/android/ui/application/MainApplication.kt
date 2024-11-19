package com.picpay.desafio.android.ui.application

import android.app.Application
import com.picpay.desafio.android.data.common.di.LocalModule
import com.picpay.desafio.android.data.common.di.RemoteModule
import com.picpay.desafio.android.data.user.di.UserModule
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
                UserModule.localModule,
                UserModule.remoteModule,
                UserModule.userModule
            )
        }
    }
}