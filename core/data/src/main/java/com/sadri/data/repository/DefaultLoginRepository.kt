package com.sadri.data.repository

import com.sadri.data.model.asEntity
import com.sadri.model.UserEntity
import com.sadri.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultLoginRepository @Inject constructor(
  private val dataSource: NetworkDataSource
) : LoginRepository {
  override fun getUser(username: String): Flow<Result<UserEntity>> {
    return flow {
      dataSource.getUser(username)
        .onSuccess {
          emit(Result.success(it.asEntity()))
        }
        .onFailure { emit(Result.failure(it)) }
    }
  }
}