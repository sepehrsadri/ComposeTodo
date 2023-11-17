package com.sadri.domain

import com.sadri.data.repository.AppConfigRepository
import com.sadri.model.LocalUserEntity
import javax.inject.Inject

class SaveLocalUserUseCase @Inject constructor(
  private val appConfigRepository: AppConfigRepository
) {
  suspend operator fun invoke(localUserEntity: LocalUserEntity) =
    appConfigRepository.saveUser(localUserEntity)
}