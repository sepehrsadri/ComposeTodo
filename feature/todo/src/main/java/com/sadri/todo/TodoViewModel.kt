package com.sadri.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.GetTodoListUseCase
import com.sadri.domain.GetLocalUserUseCase
import com.sadri.model.TodoItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
  private val todoListUseCase: GetTodoListUseCase,
  private val getLocalUserUseCase: GetLocalUserUseCase
) : ViewModel() {
  private val _uiState: MutableStateFlow<TodoUiState> = MutableStateFlow(TodoUiState.Loading)
  val uiState: StateFlow<TodoUiState> = _uiState

  init {
    fetchTodoList()
  }

  fun retry() = fetchTodoList()

  private fun fetchTodoList() {
    _uiState.value = TodoUiState.Loading
    viewModelScope.launch {
      getLocalUserUseCase.invoke().collect { user ->
        user.id?.let { id ->
          todoListUseCase.invoke(userId = id).collect { result ->
            result
              .onSuccess {
                _uiState.value = TodoUiState.Success(it)
              }
              .onFailure {
                _uiState.value = TodoUiState.Error(it)
              }
          }
        }
      }
    }
  }
}

sealed interface TodoUiState {
  data class Success(val todoList: List<TodoItemEntity>) : TodoUiState

  data class Error(val error: Throwable) : TodoUiState

  data object Loading : TodoUiState
}