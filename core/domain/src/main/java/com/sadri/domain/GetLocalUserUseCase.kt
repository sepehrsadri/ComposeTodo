package com.sadri.domain

import com.sadri.data.repository.AppConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
  private val appConfigRepository: AppConfigRepository
) {
  operator fun invoke(): Flow<Result<String>> =
    appConfigRepository.getUserId()
}