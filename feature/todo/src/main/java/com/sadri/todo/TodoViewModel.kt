package com.sadri.todo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.GetTodoListUseCase
import com.sadri.model.TodoItemEntity
import com.sadri.todo.navigation.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val todoListUseCase: GetTodoListUseCase
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
      savedStateHandle.getStateFlow<String?>(
        key = USER_ID,
        initialValue = null
      ).collect { userId ->
        userId?.let { id ->
          todoListUseCase.invoke(userId = getUserId(id)).collect { result ->
            result
              .onSuccess {
                _uiState.value = TodoUiState.Success(it)
              }
              .onFailure {
                _uiState.value = TodoUiState.Error
              }
          }
        }
      }
    }
  }

  private fun getUserId(userId: String) = userId.filter { it != '{' && it != '}' }
}

sealed interface TodoUiState {
  data class Success(val todoList: List<TodoItemEntity>) : TodoUiState

  data object Error : TodoUiState

  data object Loading : TodoUiState
}