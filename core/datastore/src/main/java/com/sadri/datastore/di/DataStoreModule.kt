package com.sadri.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sadri.datastore.appConfigDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

  @Provides
  @Singleton
  @AppConfigDataStoreQualifier
  fun provideAppConfigDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
    return context.appConfigDataStore
  }
}