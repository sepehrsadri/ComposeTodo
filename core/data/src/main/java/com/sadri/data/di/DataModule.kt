package com.sadri.data.di

import com.sadri.data.repository.DefaultLoginRepository
import com.sadri.data.repository.DefaultTodoRepository
import com.sadri.data.repository.LoginRepository
import com.sadri.data.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

  @Binds
  fun bindLoginRepository(
    loginRepository: DefaultLoginRepository
  ): LoginRepository

  @Binds
  fun bindTodoRepository(
    todoRepository: DefaultTodoRepository
  ): TodoRepository
}