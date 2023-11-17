package com.sadri.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sadri.todo.TodoScreenRoute

const val USER_ID = "userId"
const val todoNavigationRoute = "todo_route"

fun NavController.navigateToTodoScreen(navOptions: NavOptions? = null) {
  navigate(todoNavigationRoute, navOptions)
}

fun NavGraphBuilder.todoScreen() {
  composable(
    route = todoNavigationRoute,
  ) {
    TodoScreenRoute()
  }
}