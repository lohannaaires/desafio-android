package com.picpay.desafio.android.data.user.di

import com.picpay.desafio.android.data.user.remote.PicPayService
import com.picpay.desafio.android.data.user.repository.UserRepository
import com.picpay.desafio.android.data.user.repository.UserRepositoryImpl
import com.picpay.desafio.android.ui.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

object UserModule {
    private fun provideService(retrofit: Retrofit): PicPayService =
        retrofit.create(PicPayService::class.java)

    val userModule = module {
        single { provideService(get()) }
        singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
        viewModelOf(::UserViewModel)
    }
}