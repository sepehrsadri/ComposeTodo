package com.sadri.composemocktodo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sadri.composemocktodo.ui.AppState
import com.sadri.login.navigation.loginRoute
import com.sadri.login.navigation.loginScreen

@Composable
fun NavHost(
  appState: AppState,
  onShowSnackbar: suspend (String, String?) -> Boolean,
  modifier: Modifier = Modifier,
  startDestination: String = loginRoute,
) {
  val navController = appState.navController
  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
  ) {
    loginScreen(
      onSubmitSuccessfully = {},
      onShowSnackbar = onShowSnackbar
    )
  }
}