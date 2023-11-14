package com.sadri.network.api

import com.sadri.network.model.TodoItemResponse
import com.sadri.network.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
  @GET(value = "users")
  suspend fun getUsers(
    @Query("username") username: String
  ): List<UserResponse>

  @GET(value = "todos")
  suspend fun getTodosList(
    @Query("userId") userId: String
  ): List<TodoItemResponse>
}