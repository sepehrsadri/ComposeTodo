package com.sadri.data.repository

import com.sadri.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
  fun getTodoItems(userId: String): Flow<Result<List<TodoItemEntity>>>
}