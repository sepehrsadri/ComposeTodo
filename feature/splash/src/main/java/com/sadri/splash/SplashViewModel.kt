package com.sadri.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.GetLocalUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val getLocalUserUseCase: GetLocalUserUseCase
) : ViewModel() {
  private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState.Loading)
  val uiState: StateFlow<SplashUiState> = _uiState

  init {
    viewModelScope.launch {
      getLocalUserUseCase.invoke().collect { result ->
        if (result.id == null || result.username == null) {
          _uiState.value = SplashUiState.Destination("login")
        } else {
          _uiState.value = SplashUiState.Destination("todo_route")
        }
      }
    }
  }

}

sealed interface SplashUiState {
  data object Loading : SplashUiState

  data class Destination(val route: String) : SplashUiState
}