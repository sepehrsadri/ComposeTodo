package com.sadri.network.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoItemResponse(
  val userId: Int,
  val id: Int,
  val title: String,
  val completed: Boolean
)
