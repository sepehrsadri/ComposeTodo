package com.sadri.network

import com.sadri.network.api.NetworkService
import com.sadri.network.model.TodoItemResponse
import com.sadri.network.model.UserResponse
import com.sadri.network.utils.safeApiCall
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
  private val networkService: NetworkService
) {
  suspend fun getUser(username: String): Result<UserResponse> =
    safeApiCall {
      networkService.getUsers(username).first()
    }

  suspend fun getTodosList(userId: String): Result<List<TodoItemResponse>> =
    safeApiCall {
      networkService.getTodosList(userId)
    }

}