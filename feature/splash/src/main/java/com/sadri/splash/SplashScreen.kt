package com.sadri.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sadri.designsystem.component.Loading
import com.sadri.designsystem.theme.space

@Composable
internal fun SplashRoute(
  modifier: Modifier = Modifier,
) = LandingScreen(modifier)

@Composable
internal fun LandingScreen(
  modifier: Modifier
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(MaterialTheme.space.medium)
  ) {
    Loading()
  }
}
