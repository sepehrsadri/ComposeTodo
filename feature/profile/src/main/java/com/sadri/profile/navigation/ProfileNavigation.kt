package com.sadri.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sadri.profile.ProfileRoute

const val profileRoute = "profile"


fun NavController.navigateToProfileScreen(navOptions: NavOptions? = null) {
  navigate(profileRoute, navOptions)
}

fun NavGraphBuilder.profileScreen(
) {
  composable(profileRoute) {
    ProfileRoute()
  }
}