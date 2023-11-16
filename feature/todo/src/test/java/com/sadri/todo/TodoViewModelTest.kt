package com.sadri.todo

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.sadri.data.repository.TodoRepository
import com.sadri.domain.GetTodoListUseCase
import com.sadri.testing.repository.TestTodoRepository
import com.sadri.testing.util.MainDispatcherRule
import com.sadri.todo.navigation.USER_ID
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TodoViewModelTest {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val todoRepository: TodoRepository = TestTodoRepository()
  private val todoUseCase = GetTodoListUseCase(todoRepository)

  private lateinit var viewModel: TodoViewModel


  @Test
  fun stateIsInitiallyLoading() = runTest {
    viewModel = TodoViewModel(
      savedStateHandle = SavedStateHandle(mapOf(USER_ID to "{10}")),
      todoListUseCase = todoUseCase
    )
    assertEquals(
      TodoUiState.Loading,
      viewModel.uiState.value,
    )
  }

  @Test
  fun stateIsSuccess() = runTest {
    viewModel = TodoViewModel(
      savedStateHandle = SavedStateHandle(mapOf(USER_ID to TestTodoRepository.SUCCESSFULLY_FETCH_TODO)),
      todoListUseCase = todoUseCase
    )

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      val todoList = ((uiState) as TodoUiState.Success).todoList
      assertEquals(
        4,
        todoList.size
      )
    }
  }

  @Test
  fun stateIsFailureListEmpty() = runTest {
    viewModel = TodoViewModel(
      savedStateHandle = SavedStateHandle(mapOf(USER_ID to TestTodoRepository.FAILURE_LIST_EMPTY)),
      todoListUseCase = todoUseCase
    )

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      Assert.assertTrue(uiState is TodoUiState.Error)

      val error = ((uiState) as TodoUiState.Error).error
      assertEquals(
        "Something went wrong! = no todo items found",
        error.message
      )
    }
  }

  @Test
  fun stateIsFailureUnexpected() = runTest {
    viewModel = TodoViewModel(
      savedStateHandle = SavedStateHandle(mapOf(USER_ID to TestTodoRepository.FAILURE_TODO_UNEXPECTED)),
      todoListUseCase = todoUseCase
    )

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      Assert.assertTrue(uiState is TodoUiState.Error)

      val error = ((uiState) as TodoUiState.Error).error
      assertEquals(
        "Something went wrong! = Unexpected code",
        error.message
      )
    }
  }

  @Test
  fun idReformationIsDone() = runTest {
    viewModel = TodoViewModel(
      savedStateHandle = SavedStateHandle(mapOf(USER_ID to "{10}")),
      todoListUseCase = todoUseCase
    )

    advanceUntilIdle()

    viewModel.uiState.test {
      val uiState = awaitItem()

      val todoList = ((uiState) as TodoUiState.Success).todoList
      assertEquals(
        4,
        todoList.size
      )
    }
  }
}