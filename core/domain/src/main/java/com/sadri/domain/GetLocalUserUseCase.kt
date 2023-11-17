package com.sadri.domain

import com.sadri.data.repository.AppConfigRepository
import com.sadri.model.LocalUserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUseCase @Inject constructor(
  private val appConfigRepository: AppConfigRepository
) {
  operator fun invoke(): Flow<LocalUserEntity> =
    appConfigRepository.getLocalUser()
}