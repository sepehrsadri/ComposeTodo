package com.sadri.composemocktodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sadri.composemocktodo.ui.App
import com.sadri.data.util.NetworkMonitor
import com.sadri.designsystem.theme.ComposeMockTodoTheme
import com.sadri.splash.SplashUiState
import com.sadri.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var networkMonitor: NetworkMonitor


  private val viewModel by viewModels<SplashViewModel>()

  @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installSplashScreen().apply {
      setKeepOnScreenCondition {
        viewModel.uiState.value == SplashUiState.Loading
      }
    }
    setContent {
      ComposeMockTodoTheme {
        App(
          windowSizeClass = calculateWindowSizeClass(this),
          networkMonitor = networkMonitor
        )
      }
    }
  }
}
