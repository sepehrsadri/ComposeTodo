package com.sadri.data.repository

import com.sadri.common.utils.toNetworkException
import com.sadri.data.model.asEntity
import com.sadri.model.AppException
import com.sadri.model.TodoItemEntity
import com.sadri.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultTodoRepository @Inject constructor(
  private val dataSource: NetworkDataSource
) : TodoRepository {
  override fun getTodoItems(userId: String): Flow<Result<List<TodoItemEntity>>> {
    return flow {
      dataSource.getTodosList(userId)
        .onSuccess { items ->
          if (items.isEmpty()) {
            emit(Result.failure(AppException.EmptyList))
          } else {
            emit(Result.success(items.map { it.asEntity() }))
          }
        }
        .onFailure { emit(Result.failure(it.toNetworkException())) }
    }
  }
}