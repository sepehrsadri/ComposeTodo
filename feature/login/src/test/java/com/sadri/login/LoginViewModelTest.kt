package com.sadri.login

import app.cash.turbine.test
import com.sadri.data.repository.LoginRepository
import com.sadri.domain.LoginUseCase
import com.sadri.testing.repository.TestLoginRepository
import com.sadri.testing.repository.TestLoginRepository.Companion.FAILURE_USER_NOT_FOUND
import com.sadri.testing.repository.TestLoginRepository.Companion.SUCCESSFULLY_GET_USER
import com.sadri.testing.util.MainDispatcherRule
import kotlinx.coroutines.cancel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val loginRepository: LoginRepository = TestLoginRepository()
  private val loginUseCase = LoginUseCase(loginRepository)

  private lateinit var viewModel: LoginViewModel

  @Before
  fun setup() {
    viewModel = LoginViewModel(loginUseCase)
  }

  @Test
  fun stateIsInitiallyNothing() = runTest {
    assertEquals(
      LoginUiState.Nothing,
      viewModel.uiState.value,
    )
  }

  @Test
  fun stateIsSuccess() = runTest {
    viewModel.login(SUCCESSFULLY_GET_USER)

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      val user = ((uiState) as LoginUiState.Success).userEntity
      assertEquals(
        10,
        user.id
      )
      assertEquals(
        "Clementina DuBuque",
        user.name
      )
      assertEquals(
        "Moriah.Stanton",
        user.username
      )
    }
  }

  @Test
  fun stateIsFailureUserNotFound() = runTest {
    viewModel.login(FAILURE_USER_NOT_FOUND)

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      assertTrue(uiState is LoginUiState.Error)

      val error = ((uiState) as LoginUiState.Error).error
      assertEquals(
        "Something went wrong! = User not found",
        error.message
      )
    }
  }

  @Test
  fun stateIsFailureUnexpected() = runTest {
    viewModel.login(TestLoginRepository.FAILURE_USER_UNEXPECTED)

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      assertTrue(uiState is LoginUiState.Error)

      val error = ((uiState) as LoginUiState.Error).error
      assertEquals(
        "Something went wrong! = Unexpected code",
        error.message
      )
    }
  }

  @Test
  fun stateGoesToLoading() = runTest {
    val captureStates = mutableListOf<LoginUiState>()
    val job = backgroundScope.launch {
      var counter = 0
      viewModel.uiState.collect {
        if (counter + 1 == 2) {
          cancel()
        }
        counter++
        captureStates.add(it)
      }
    }

    advanceUntilIdle()

    viewModel.login(SUCCESSFULLY_GET_USER)

    joinAll(job)

    assertEquals(
      LoginUiState.Loading,
      captureStates.first(),
    )
    assertTrue(captureStates[1] is LoginUiState.Success)
  }
}