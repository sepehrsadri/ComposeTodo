package com.sadri.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sadri.login.LoginRoute

const val loginRoute = "login"

fun NavGraphBuilder.loginScreen(
  onSubmitSuccessfully: (String) -> Unit,
  onShowSnackbar: suspend (String, String?) -> Boolean,
) {
  composable(loginRoute) {
    LoginRoute(
      onSubmitSuccessfully = onSubmitSuccessfully,
      onShowSnackbar = onShowSnackbar
    )
  }
}