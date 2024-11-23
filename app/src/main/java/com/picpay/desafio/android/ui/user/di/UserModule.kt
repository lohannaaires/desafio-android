package com.picpay.desafio.android.ui.user.di

import com.picpay.desafio.android.ui.user.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object UserModule {
    val viewModelModule = module {
        viewModelOf(::UserViewModel)
    }
}