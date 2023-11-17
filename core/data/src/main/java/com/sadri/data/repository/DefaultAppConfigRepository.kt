package com.sadri.data.repository

import com.sadri.datastore.AppConfigLocalDataSource
import com.sadri.model.LocalUserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAppConfigRepository @Inject constructor(
  private val dataSource: AppConfigLocalDataSource
) : AppConfigRepository {
  override fun getLocalUser(): Flow<LocalUserEntity> =
    dataSource.getLocalUser()

  override suspend fun saveUser(localUserEntity: LocalUserEntity) =
    dataSource.saveUser(localUserEntity)
}