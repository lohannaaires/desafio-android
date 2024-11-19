package com.picpay.desafio.android.user

import com.nhaarman.mockitokotlin2.*
import com.picpay.desafio.android.data.user.db.UserEntity
import com.picpay.desafio.android.data.user.local.UserLocalService
import com.picpay.desafio.android.data.user.model.UserMap
import com.picpay.desafio.android.data.user.model.UserModel
import com.picpay.desafio.android.data.user.remote.PicPayService
import com.picpay.desafio.android.data.user.repository.UserRepository
import com.picpay.desafio.android.data.user.repository.UserRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryTest {
    private val remoteService: PicPayService = mock()
    private val localService: UserLocalService = mock()
    private lateinit var repository: UserRepository

    private lateinit var cachedUsers: List<UserEntity>
    private lateinit var apiUsers: List<UserModel>

    @Before
    fun setup() {
        repository = UserRepositoryImpl(remoteService, localService)

        cachedUsers = listOf(
            UserEntity(
                id = 0,
                name = "Israel Dias",
                img = "https://avatars.githubusercontent.com/u/42253217?v=4",
                username = "israelld"
            )
        )

        apiUsers = listOf(
            UserModel(
                id = 0,
                name = "Israel Dias",
                img = "https://avatars.githubusercontent.com/u/42253217?v=4",
                username = "israelld"
            ),
            UserModel(
                id = 1,
                name = "Lohanna Aires",
                img = "https://avatars.githubusercontent.com/u/6319151?v=4",
                username = "lohannaaires"
            )
        )
    }

    @Test
    fun `fetchAndCacheUsers should update cache when API returns a different list`() = runTest {
        // Configuração
        val response = Response.success(apiUsers)

        whenever(localService.getUsers()).thenReturn(cachedUsers)
        whenever(remoteService.getUsers()).thenReturn(response)
        whenever(localService.cleanUsers()).then { /* */ }
        whenever(localService.saveUsers(any())).then { /* */ }

        // Execução
        repository.fetchAndCacheUsers()

        // Verificação
        verify(localService).cleanUsers()
        verify(localService).saveUsers(UserMap.toDBList(apiUsers))
    }

    @Test
    fun `fetchAndCacheUsers should not update cache when API returns the same list`() = runTest {
        // Configuração
        val apiUsers = UserMap.fromDBList(cachedUsers)
        val response = Response.success(apiUsers)

        whenever(localService.getUsers()).thenReturn(cachedUsers)
        whenever(remoteService.getUsers()).thenReturn(response)

        // Execução
        repository.fetchAndCacheUsers()

        // Verificação
        verify(localService, never()).cleanUsers()
        verify(localService, never()).saveUsers(any())
    }

    @Test(expected = RuntimeException::class)
    fun `fetchAndCacheUsers should handle API error`() = runTest {
        // Configuração
        whenever(localService.getUsers()).thenReturn(emptyList())
        whenever(remoteService.getUsers()).thenThrow(RuntimeException("Error message"))

        // Execução
        repository.fetchAndCacheUsers()

        // Verificação
        verify(localService, never()).cleanUsers()
        verify(localService, never()).saveUsers(any())
    }
}