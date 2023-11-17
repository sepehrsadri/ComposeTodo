package com.sadri.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sadri.datastore.di.AppConfigDataStoreQualifier
import com.sadri.model.AppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppConfigLocalDataSource @Inject constructor(
  @AppConfigDataStoreQualifier private val dataStore: DataStore<Preferences>
) {
  suspend fun saveUserId(userId: String?) {
    dataStore.edit {
      if (userId == null) {
        it.remove(USER_ID_KEY)
      } else {
        it[USER_ID_KEY] = userId
      }
    }
  }

  fun getUserId(): Flow<Result<String>> {
    return dataStore.data.map {
      val id = it[USER_ID_KEY]
      if (id != null) {
        Result.success(id)
      } else {
        Result.failure(AppException.EmptyUserId)
      }
    }
  }

  companion object {
    private val USER_ID_KEY = stringPreferencesKey("userIdKey")
  }
}