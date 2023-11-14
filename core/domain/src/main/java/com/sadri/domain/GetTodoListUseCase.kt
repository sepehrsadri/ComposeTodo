package com.sadri.domain

import com.sadri.data.repository.TodoRepository
import com.sadri.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
  private val todoRepository: TodoRepository
) {
  operator fun invoke(userId: String): Flow<Result<List<TodoItemEntity>>> =
    todoRepository.getTodoItems(userId)
}