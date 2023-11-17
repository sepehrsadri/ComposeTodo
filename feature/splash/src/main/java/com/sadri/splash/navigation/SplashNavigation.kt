package com.sadri.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sadri.splash.SplashRoute

const val splashRoute = "splash"

fun NavGraphBuilder.splashScreen(
) {
  composable(splashRoute) {
    SplashRoute()
  }
}