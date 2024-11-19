package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.common.di.RemoteModule.networkModule
import com.picpay.desafio.android.data.common.local.AppDatabase
import com.picpay.desafio.android.data.user.db.UserDao
import com.picpay.desafio.android.data.user.di.UserModule.remoteModule
import com.picpay.desafio.android.data.user.di.UserModule.userModule
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.Module
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito.mock

class KoinModuleTest : KoinTest {
    private val mockDatabase: AppDatabase = mock(AppDatabase::class.java)
    private val mockDao: UserDao = mock(UserDao::class.java)

    private val dbModule = module {
        single { mockDatabase }
    }

    private val localModule = module {
        single { mockDao }
    }

    private val appModules: List<Module> = listOf(
        dbModule,
        networkModule,
        localModule,
        remoteModule,
        userModule
    )

    @Before
    fun setup() {
        startKoin {
            modules(appModules)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check Koin modules`() {
        koinApplication {
            modules(appModules)
        }.checkModules()
    }
}