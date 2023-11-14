package com.sadri.domain

import com.sadri.data.repository.LoginRepository
import com.sadri.model.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
  private val loginRepository: LoginRepository
) {
  operator fun invoke(username: String): Flow<Result<UserEntity>> =
    loginRepository.getUser(username)
}