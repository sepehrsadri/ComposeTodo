package com.sadri.data.repository

import com.sadri.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
  fun getUser(username: String): Flow<Result<UserEntity>>
}