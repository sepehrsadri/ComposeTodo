package com.sadri.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.LoginUseCase
import com.sadri.domain.SaveLocalUserUseCase
import com.sadri.model.LocalUserEntity
import com.sadri.model.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val saveLocalUserUseCase: SaveLocalUserUseCase
) : ViewModel() {
  private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Nothing)
  val uiState: StateFlow<LoginUiState> = _uiState

  fun login(username: String) {
    _uiState.value = LoginUiState.Loading
    viewModelScope.launch {
      loginUseCase.invoke(username).collect { result ->
        result
          .onSuccess {
            saveLocalUserUseCase.invoke(
              LocalUserEntity(
                id = it.id.toString(),
                username = it.username
              )
            )
            _uiState.value = LoginUiState.Success(it)
          }
          .onFailure {
            _uiState.value = LoginUiState.Error(it)
          }
      }
    }
  }

}

sealed interface LoginUiState {
  data class Success(val userEntity: UserEntity) : LoginUiState

  data class Error(val error: Throwable) : LoginUiState

  data object Loading : LoginUiState

  data object Nothing : LoginUiState
}