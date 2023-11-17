package com.sadri.composemocktodo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val title: String
) {
  TODO(
    selectedIcon = Icons.Rounded.List,
    unselectedIcon = Icons.Rounded.List,
    title = "Todo"
  ),
  PROFILE(
    selectedIcon = Icons.Rounded.AccountCircle,
    unselectedIcon = Icons.Rounded.AccountCircle,
    title = "Profile"
  )
}