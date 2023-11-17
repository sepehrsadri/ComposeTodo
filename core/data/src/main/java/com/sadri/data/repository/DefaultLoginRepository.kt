package com.sadri.data.repository

import com.sadri.common.utils.toNetworkException
import com.sadri.data.model.asEntity
import com.sadri.model.AppException
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
        .onSuccess { user ->
          if (user == null) {
            emit(Result.failure(AppException.UserNotFound))
          } else {
            emit(Result.success(user.asEntity()))
          }
        }
        .onFailure { emit(Result.failure(it.toNetworkException())) }
    }
  }
}