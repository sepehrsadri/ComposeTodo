package com.sadri.testing.repository

import com.sadri.data.repository.TodoRepository
import com.sadri.model.NetworkException
import com.sadri.model.TodoItemEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestTodoRepository : TodoRepository {
  val todoList = listOf(
    TodoItemEntity(
      userId = 10,
      id = 181,
      title = "ut cupiditate sequi aliquam fuga maiores",
      completed = false
    ),
    TodoItemEntity(
      userId = 10,
      id = 182,
      title = "inventore saepe cumque et aut illum enim",
      completed = true
    ),
    TodoItemEntity(
      userId = 10,
      id = 183,
      title = "omnis nulla eum aliquam distinctio",
      completed = true
    ),
    TodoItemEntity(
      userId = 10,
      id = 184,
      title = "molestias modi perferendis perspiciatis",
      completed = false
    )
  )


  override fun getTodoItems(userId: String): Flow<Result<List<TodoItemEntity>>> {
    return flow {
      delay(500)

      when (userId) {
        SUCCESSFULLY_FETCH_TODO -> {
          emit(Result.success(todoList))
        }
        FAILURE_LIST_EMPTY -> {
          emit(Result.failure(NetworkException.EmptyList))
        }
        FAILURE_TODO_UNEXPECTED -> {
          emit(Result.failure(NetworkException.Unexpected))
        }
        "10" -> {
          emit(Result.success(todoList))
        }
        else -> {
          throw IllegalStateException()
        }
      }
    }
  }

  companion object {
    const val SUCCESSFULLY_FETCH_TODO = "SUCCESSFULLY_FETCH_TODO"
    const val FAILURE_LIST_EMPTY = "FAILURE_LIST_EMPTY"
    const val FAILURE_TODO_UNEXPECTED = "FAILURE_TODO_UNEXPECTED"
  }
}