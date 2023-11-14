package com.sadri.data.model

import com.sadri.model.TodoItemEntity
import com.sadri.network.model.TodoItemResponse

fun TodoItemResponse.asEntity() = TodoItemEntity(
  userId = userId,
  id = id,
  title = title,
  completed = completed
)