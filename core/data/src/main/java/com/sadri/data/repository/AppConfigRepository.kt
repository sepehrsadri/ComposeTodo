package com.sadri.data.repository

import kotlinx.coroutines.flow.Flow


interface AppConfigRepository {
  fun getUserId(): Flow<Result<String>>
  suspend fun saveUserId(userId: String?)
}