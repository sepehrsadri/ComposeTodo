package com.sadri.composemocktodo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sadri.composemocktodo.ui.AppState
import com.sadri.login.navigation.loginScreen
import com.sadri.profile.navigation.profileRoute
import com.sadri.profile.navigation.profileScreen
import com.sadri.splash.navigation.splashScreen
import com.sadri.todo.navigation.navigateToTodoScreen
import com.sadri.todo.navigation.todoScreen

@Composable
fun NavHost(
  appState: AppState,
  onShowSnackbar: suspend (String, String?) -> Boolean,
  modifier: Modifier = Modifier,
  startDestination: String,
) {
  val navController = appState.navController
  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
  ) {
    loginScreen(
      onSubmitSuccessfully = { navController.navigateToTodoScreen() },
      onShowSnackbar = onShowSnackbar
    )
    todoScreen()
    splashScreen()
    profileScreen()
  }
}