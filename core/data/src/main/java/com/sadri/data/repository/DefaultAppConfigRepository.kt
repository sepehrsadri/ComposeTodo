package com.sadri.data.repository

import com.sadri.datastore.AppConfigLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAppConfigRepository @Inject constructor(
  private val dataSource: AppConfigLocalDataSource
) : AppConfigRepository {
  override fun getUserId(): Flow<Result<String>> =
    dataSource.getUserId()

  override suspend fun saveUserId(userId: String?) {
    dataSource.saveUserId(userId)
  }
}