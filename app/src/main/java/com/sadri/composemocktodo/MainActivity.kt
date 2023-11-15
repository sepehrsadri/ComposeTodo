package com.sadri.composemocktodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.sadri.composemocktodo.ui.App
import com.sadri.data.util.NetworkMonitor
import com.sadri.designsystem.theme.ComposeMockTodoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var networkMonitor: NetworkMonitor

  @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
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
