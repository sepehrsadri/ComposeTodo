package com.sadri.testing.repository

import com.sadri.data.repository.LoginRepository
import com.sadri.model.AppException
import com.sadri.model.UserEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestLoginRepository : LoginRepository {
  override fun getUser(username: String): Flow<Result<UserEntity>> {
    return flow {
      delay(500)
      when (username) {
        SUCCESSFULLY_GET_USER -> {
          emit(
            Result.success(
              UserEntity(
                id = 10,
                name = "Clementina DuBuque",
                username = "Moriah.Stanton",
                email = "Rey.Padberg@karina.biz",
                phone = "024-648-3804"
              )
            )
          )
        }
        FAILURE_USER_NOT_FOUND -> {
          emit(Result.failure(AppException.UserNotFound))
        }
        FAILURE_USER_UNEXPECTED -> {
          emit(Result.failure(AppException.Unexpected))
        }
        else -> {
          throw IllegalStateException()
        }
      }
    }
  }

  companion object {
    const val SUCCESSFULLY_GET_USER = "SUCCESSFULLY_GET_USER"
    const val FAILURE_USER_NOT_FOUND = "FAILURE_USER_NOT_FOUND"
    const val FAILURE_USER_UNEXPECTED = "FAILURE_USER_UNEXPECTED"
  }
}