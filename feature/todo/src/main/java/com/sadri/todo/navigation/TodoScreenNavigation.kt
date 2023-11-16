package com.sadri.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sadri.todo.TodoScreenRoute

const val USER_ID = "userId"
const val todoNavigationRoute = "todo_route/{$USER_ID}"

fun NavController.navigateToTodoScreen(userId: String) {
  navigate(createRoute(userId))
}

fun NavGraphBuilder.todoScreen() {
  composable(
    route = todoNavigationRoute,
    arguments = listOf(
      navArgument(USER_ID) { type = NavType.StringType }
    )
  ) {
    TodoScreenRoute()
  }
}

private fun createRoute(userId: String) = "todo_route/{$userId}"