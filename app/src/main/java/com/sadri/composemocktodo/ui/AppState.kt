package com.sadri.composemocktodo.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sadri.composemocktodo.navigation.TopLevelDestination
import com.sadri.data.util.NetworkMonitor
import com.sadri.profile.navigation.navigateToProfileScreen
import com.sadri.todo.navigation.navigateToTodoScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberAppState(
  windowSizeClass: WindowSizeClass,
  networkMonitor: NetworkMonitor,
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  navController: NavHostController = rememberNavController(),
): AppState {
  return remember(
    navController,
    coroutineScope,
    windowSizeClass,
    networkMonitor,
  ) {
    AppState(
      navController,
      coroutineScope,
      windowSizeClass,
      networkMonitor
    )
  }
}

@Stable
class AppState(
  val navController: NavHostController,
  val coroutineScope: CoroutineScope,
  val windowSizeClass: WindowSizeClass,
  networkMonitor: NetworkMonitor,
) {
  val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination

  val isOffline = networkMonitor.isOnline
    .map(Boolean::not)
    .stateIn(
      scope = coroutineScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = false,
    )

  val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

  fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    val topLevelNavOptions = navOptions {
      // Pop up to the start destination of the graph to
      // avoid building up a large stack of destinations
      // on the back stack as users select items
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      // Avoid multiple copies of the same destination when
      // reselecting the same item
      launchSingleTop = true
      // Restore state when reselecting a previously selected item
      restoreState = true
    }

    when (topLevelDestination) {
      TopLevelDestination.TODO -> navController.navigateToTodoScreen(topLevelNavOptions)
      TopLevelDestination.PROFILE -> navController.navigateToProfileScreen(topLevelNavOptions)
    }
  }
}
