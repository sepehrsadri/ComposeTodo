package com.sadri.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sadri.login.LoginRoute

const val loginRoute = "login"

fun NavGraphBuilder.loginScreen(
  onSubmitSuccessfully: () -> Unit,
) {
  composable(loginRoute) {
    LoginRoute(
      onSubmitSuccessfully = onSubmitSuccessfully
    )
  }
}