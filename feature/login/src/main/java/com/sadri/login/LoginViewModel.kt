package com.sadri.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.LoginUseCase
import com.sadri.model.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase
) : ViewModel() {
  private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Nothing)
  val uiState: StateFlow<LoginUiState> = _uiState

  fun login(username: String) {
    _uiState.value = LoginUiState.Loading
    viewModelScope.launch {
      loginUseCase.invoke(username).collect { result ->
        result
          .onSuccess {
            _uiState.value = LoginUiState.Success(it)
          }
          .onFailure {
            _uiState.value = LoginUiState.Error
          }
      }
    }
  }

}

sealed interface LoginUiState {
  data class Success(val userEntity: UserEntity) : LoginUiState

  data object Error : LoginUiState

  data object Loading : LoginUiState

  data object Nothing : LoginUiState
}