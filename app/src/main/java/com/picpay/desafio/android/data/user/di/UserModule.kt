package com.picpay.desafio.android.data.user.di

import com.picpay.desafio.android.data.common.local.AppDatabase
import com.picpay.desafio.android.data.user.local.UserLocalService
import com.picpay.desafio.android.data.user.local.UserLocalServiceImpl
import com.picpay.desafio.android.data.user.remote.PicPayService
import com.picpay.desafio.android.data.user.repository.UserRepository
import com.picpay.desafio.android.data.user.repository.UserRepositoryImpl
import com.picpay.desafio.android.ui.user.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

object UserModule {
    private fun provideRemoteService(retrofit: Retrofit): PicPayService =
        retrofit.create(PicPayService::class.java)

    val userModule = module {
        single { get<AppDatabase>().userDao() }
        single { provideRemoteService(get()) }
        single<UserLocalService> { UserLocalServiceImpl(get()) }
        singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
        viewModelOf(::UserViewModel)
    }
}