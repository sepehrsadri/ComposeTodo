package com.sadri.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadri.domain.GetLocalUserUseCase
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
class ProfileViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val getLocalUserUseCase: GetLocalUserUseCase,
  private val saveLocalUserUseCase: SaveLocalUserUseCase
) : ViewModel() {

  private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState.Loading)
  val uiState: StateFlow<ProfileUiState> = _uiState

  init {
    getUser()
  }

  fun retry() = getUser()

  fun logout() {
    viewModelScope.launch {
      saveLocalUserUseCase.invoke(
        LocalUserEntity(
          username = null,
          id = null
        )
      )
    }
  }

  private fun getUser() {
    _uiState.value = ProfileUiState.Loading
    viewModelScope.launch {
      getLocalUserUseCase.invoke().collect { user ->
        user.username?.let { username ->
          loginUseCase.invoke(username).collect { result ->
            result.onSuccess {
              _uiState.value = ProfileUiState.Success(it)
            }
              .onFailure {
                _uiState.value = ProfileUiState.Error(it)
              }
          }
        }
      }
    }
  }
}

sealed interface ProfileUiState {
  data class Success(val userEntity: UserEntity) : ProfileUiState

  data class Error(val error: Throwable) : ProfileUiState

  data object Loading : ProfileUiState
}