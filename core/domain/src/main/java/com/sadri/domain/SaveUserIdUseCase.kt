package com.sadri.domain

import com.sadri.data.repository.AppConfigRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
  private val appConfigRepository: AppConfigRepository
) {
  suspend operator fun invoke(userId: String?) =
    appConfigRepository.saveUserId(userId)
}