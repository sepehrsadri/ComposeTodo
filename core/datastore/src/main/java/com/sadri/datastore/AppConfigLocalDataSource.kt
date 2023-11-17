package com.sadri.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sadri.datastore.di.AppConfigDataStoreQualifier
import com.sadri.model.AppException
import com.sadri.model.LocalUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppConfigLocalDataSource @Inject constructor(
  @AppConfigDataStoreQualifier private val dataStore: DataStore<Preferences>
) {

  suspend fun saveUser(localUserEntity: LocalUserEntity) {
    saveUserId(localUserEntity.id)
    saveUsername(localUserEntity.username)
  }

  fun getLocalUser(): Flow<LocalUserEntity> {
    return dataStore.data.map {
      val username = it[USER_NAME_KEY]
      val id = it[USER_ID_KEY]
      LocalUserEntity(
        id = id,
        username = username
      )
    }
  }

  private suspend fun saveUsername(userName: String?) {
    dataStore.edit {
      if (userName == null) {
        it.remove(USER_NAME_KEY)
      } else {
        it[USER_NAME_KEY] = userName
      }
    }
  }

  private suspend fun saveUserId(userId: String?) {
    dataStore.edit {
      if (userId == null) {
        it.remove(USER_ID_KEY)
      } else {
        it[USER_ID_KEY] = userId
      }
    }
  }

  companion object {
    private val USER_ID_KEY = stringPreferencesKey("userIdKey")
    private val USER_NAME_KEY = stringPreferencesKey("userNameKey")
  }
}