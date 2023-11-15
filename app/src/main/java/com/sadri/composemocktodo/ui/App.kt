package com.sadri.composemocktodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadri.composemocktodo.navigation.NavHost
import com.sadri.data.util.NetworkMonitor
import com.sadri.designsystem.component.Background
import com.sadri.designsystem.component.GradientBackground
import com.sadri.designsystem.theme.GradientColors
import com.sadri.designsystem.theme.LocalGradientColors

@OptIn(
  ExperimentalMaterial3Api::class,
  ExperimentalLayoutApi::class,
  ExperimentalComposeUiApi::class,
)
@Composable
fun App(
  windowSizeClass: WindowSizeClass,
  networkMonitor: NetworkMonitor,
  appState: AppState = rememberAppState(
    networkMonitor = networkMonitor,
    windowSizeClass = windowSizeClass
  ),
) {
  // can get it as a config from server
  val shouldShowGradientBackground = true
  Background {
    GradientBackground(
      gradientColors = if (shouldShowGradientBackground) {
        LocalGradientColors.current
      } else {
        GradientColors()
      },
    ) {
      val snackbarHostState = remember { SnackbarHostState() }

      val isOffline by appState.isOffline.collectAsStateWithLifecycle()

      // If user is not connected to the internet show a snack bar to inform them.
      LaunchedEffect(isOffline) {
        if (isOffline) {
          snackbarHostState.showSnackbar(
            message = "No connection to network",
            duration = SnackbarDuration.Indefinite,
          )
        }
      }

      Scaffold(
        modifier = Modifier.semantics {
          testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
      ) { padding ->
        Row(
          Modifier
            .fillMaxSize()
            .padding(padding)
            .consumeWindowInsets(padding)
            .windowInsetsPadding(
              WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal,
              ),
            ),
        ) {

          Column(Modifier.fillMaxSize()) {
            NavHost(appState = appState, onShowSnackbar = { message, action ->
              snackbarHostState.showSnackbar(
                message = message,
                actionLabel = action,
                duration = SnackbarDuration.Short,
              ) == SnackbarResult.ActionPerformed
            })
          }

          // TODO: We may want to add padding or spacer when the snackbar is shown so that
          //  content doesn't display behind it.
        }
      }
    }
  }
}

private fun Modifier.notificationDot(): Modifier =
  composed {
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    drawWithContent {
      drawContent()
      drawCircle(
        tertiaryColor,
        radius = 5.dp.toPx(),
        // This is based on the dimensions of the NavigationBar's "indicator pill";
        // however, its parameters are private, so we must depend on them implicitly
        // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
        center = center + Offset(
          64.dp.toPx() * .45f,
          32.dp.toPx() * -.45f - 6.dp.toPx(),
        ),
      )
    }
  }