package com.picpay.desafio.android.data.user.di

import com.picpay.desafio.android.data.common.local.AppDatabase
import com.picpay.desafio.android.data.user.local.UserLocalService
import com.picpay.desafio.android.data.user.local.UserLocalServiceImpl
import com.picpay.desafio.android.data.user.remote.PicPayService
import com.picpay.desafio.android.data.user.repository.UserRepository
import com.picpay.desafio.android.data.user.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

object UserModule {
    private fun provideRemoteService(retrofit: Retrofit): PicPayService =
        retrofit.create(PicPayService::class.java)

    val localModule = module {
        single { get<AppDatabase>().userDao() }
    }

    val serviceModule = module {
        factoryOf(::UserLocalServiceImpl) { bind<UserLocalService>() }
        factory { provideRemoteService(get()) }
    }

    val repositoryModule = module {
        factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }
    }
}