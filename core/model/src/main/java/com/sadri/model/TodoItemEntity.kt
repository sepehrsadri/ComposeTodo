package com.sadri.model

data class TodoItemEntity(
  val userId: Int,
  val id: Int,
  val title: String,
  val completed: Boolean
)