package com.sadri.todo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadri.designsystem.component.Error
import com.sadri.designsystem.component.Loading
import com.sadri.designsystem.theme.space
import com.sadri.model.TodoItemEntity


@Composable
internal fun TodoScreenRoute(
  modifier: Modifier = Modifier,
  viewModel: TodoViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  TodoScreen(
    uiState = uiState,
    modifier = modifier,
    retry = viewModel::retry
  )
}

@Composable
internal fun TodoScreen(
  uiState: TodoUiState,
  modifier: Modifier = Modifier,
  retry: () -> Unit
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(MaterialTheme.space.medium)
  ) {
    when (uiState) {
      is TodoUiState.Error -> {
        Error(
          message = requireNotNull(uiState.error.message),
          retry = retry
        )
      }
      TodoUiState.Loading -> {
        Loading()
      }
      is TodoUiState.Success -> {
        TodoList(
          todoList = uiState.todoList,
          modifier = modifier
        )
      }
    }
  }
}

@Composable
private fun TodoList(
  todoList: List<TodoItemEntity>,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(MaterialTheme.space.medium)
  )
  {
    todoList.forEach { todoItem ->
      val todoItemId = todoItem.id
      item(key = todoItemId) {
        TodoItem(todoItem)
      }
    }
  }
}

@Composable
private fun TodoItem(
  todoItem: TodoItemEntity
) {
  Spacer(modifier = Modifier.height(MaterialTheme.space.small))
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.onSurface
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
  ) {
    Text(
      text = todoItem.title,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      color = MaterialTheme.colorScheme.outline,
      modifier = Modifier.padding(MaterialTheme.space.xSmall)
    )
    Checkbox(
      checked = todoItem.completed,
      onCheckedChange = {
        // no-op
      }
    )
  }

}