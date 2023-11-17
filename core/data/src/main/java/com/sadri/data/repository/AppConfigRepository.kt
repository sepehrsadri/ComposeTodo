package com.sadri.data.repository

import com.sadri.model.LocalUserEntity
import kotlinx.coroutines.flow.Flow


interface AppConfigRepository {
  fun getLocalUser(): Flow<LocalUserEntity>
  suspend fun saveUser(localUserEntity: LocalUserEntity)
}