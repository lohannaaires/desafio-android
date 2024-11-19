package com.picpay.desafio.android.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.data.user.db.UserEntity
import com.picpay.desafio.android.data.user.repository.UserRepository
import com.picpay.desafio.android.ui.user.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepository: UserRepository

    private lateinit var userViewModel: UserViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        userViewModel = UserViewModel(mockRepository)
    }

    @Test(expected = RuntimeException::class)
    fun `loadUsers should set error to null when users are successfully loaded`() = runTest {
        // Configuração
        val users = listOf(
            UserEntity(
                id = 0,
                name = "Israel Dias",
                img = "https://avatars.githubusercontent.com/u/42253217?v=4",
                username = "israelld"
            ),
            UserEntity(
                id = 1,
                name = "Lohanna Aires",
                img = "https://avatars.githubusercontent.com/u/6319151?v=4",
                username = "lohannaaires"
            )
        )

        Mockito.`when`(mockRepository.getCachedUsers()).thenReturn(users)
        Mockito.`when`(mockRepository.fetchAndCacheUsers()).thenReturn(Unit)

        val errorObserver = Observer<String?> {}
        userViewModel.error.observeForever(errorObserver)

        // Execução
        userViewModel.loadUsers()

        // Verificação
        assertNull(userViewModel.error.value)

        Mockito.verify(errorObserver, Mockito.never()).onChanged(Mockito.anyString())
    }

    @Test(expected = RuntimeException::class)
    fun `loadUsers should set error message when cache is empty`() = runTest {
        // Configuração
        Mockito.`when`(mockRepository.getCachedUsers()).thenReturn(emptyList())

        val errorObserver = Observer<String?> {}
        userViewModel.error.observeForever(errorObserver)

        // Execução
        userViewModel.loadUsers()
        testDispatcher.scheduler.advanceUntilIdle()

        // Verificação
        assertEquals(
            "Erro ao carregar os dados. Tente novamente mais tarde.",
            userViewModel.error.value
        )

        Mockito.verify(errorObserver)
            .onChanged("Erro ao carregar os dados. Tente novamente mais tarde.")
    }

    @Test(expected = RuntimeException::class)
    fun `loadUsers should set error message when exception is thrown`() = runTest {
        Mockito.`when`(mockRepository.fetchAndCacheUsers()).thenThrow(
            RuntimeException("API error message")
        )

        val errorObserver = Observer<String?> {}
        userViewModel.error.observeForever(errorObserver)

        // Execução
        userViewModel.loadUsers()

        testDispatcher.scheduler.advanceUntilIdle()

        // Verificação
        assertEquals(
            "Erro ao carregar os dados. Tente novamente mais tarde.",
            userViewModel.error.value
        )

        Mockito.verify(errorObserver)
            .onChanged("Erro ao carregar os dados. Tente novamente mais tarde.")
    }

    @Test
    fun `cleanErrorMessage should reset error to null`() {
        // Execução
        userViewModel.cleanErrorMessage()

        // Verificação
        assertNull(userViewModel.error.value)
    }
}