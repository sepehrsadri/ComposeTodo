package com.sadri.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {
  private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState.Loading)
  val uiState: StateFlow<SplashUiState> = _uiState

  init {
    viewModelScope.launch {
      getUserIdUseCase.invoke().collect { result ->
        result
          .onSuccess {
            _uiState.value = SplashUiState.Destination("todo_route/{$it}")
          }
          .onFailure {
            _uiState.value = SplashUiState.Destination("login")
          }
      }
    }
  }

}

sealed interface SplashUiState {
  data object Loading : SplashUiState

  data class Destination(val route: String) : SplashUiState
}