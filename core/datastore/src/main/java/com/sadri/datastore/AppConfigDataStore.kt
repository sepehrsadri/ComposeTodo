package com.sadri.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.appConfigDataStore: DataStore<Preferences> by preferencesDataStore(
  name = "AppConfig"
)