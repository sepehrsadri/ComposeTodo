package com.sadri.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RowScope.NiaNavigationBarItem(
  selected: Boolean,
  onClick: () -> Unit,
  icon: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  selectedIcon: @Composable () -> Unit = icon,
  enabled: Boolean = true,
  label: @Composable (() -> Unit)? = null,
  alwaysShowLabel: Boolean = true,
) {
  NavigationBarItem(
    selected = selected,
    onClick = onClick,
    icon = if (selected) selectedIcon else icon,
    modifier = modifier,
    enabled = enabled,
    label = label,
    alwaysShowLabel = alwaysShowLabel,
    colors = NavigationBarItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
  )
}

@Composable
fun NiaNavigationBar(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  NavigationBar(
    modifier = modifier,
    contentColor = NavigationDefaults.navigationContentColor(),
    tonalElevation = 0.dp,
    content = content,
  )
}


@Composable
fun NiaNavigationRailItem(
  selected: Boolean,
  onClick: () -> Unit,
  icon: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  selectedIcon: @Composable () -> Unit = icon,
  enabled: Boolean = true,
  label: @Composable (() -> Unit)? = null,
  alwaysShowLabel: Boolean = true,
) {
  NavigationRailItem(
    selected = selected,
    onClick = onClick,
    icon = if (selected) selectedIcon else icon,
    modifier = modifier,
    enabled = enabled,
    label = label,
    alwaysShowLabel = alwaysShowLabel,
    colors = NavigationRailItemDefaults.colors(
      selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedIconColor = NavigationDefaults.navigationContentColor(),
      selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
      unselectedTextColor = NavigationDefaults.navigationContentColor(),
      indicatorColor = NavigationDefaults.navigationIndicatorColor(),
    ),
  )
}

@Composable
fun NiaNavigationRail(
  modifier: Modifier = Modifier,
  header: @Composable (ColumnScope.() -> Unit)? = null,
  content: @Composable ColumnScope.() -> Unit,
) {
  NavigationRail(
    modifier = modifier,
    containerColor = Color.Transparent,
    contentColor = NavigationDefaults.navigationContentColor(),
    header = header,
    content = content,
  )
}

object NavigationDefaults {
  @Composable
  fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

  @Composable
  fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

  @Composable
  fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}