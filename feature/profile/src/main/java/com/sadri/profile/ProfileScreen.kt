package com.sadri.profile

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadri.designsystem.component.Error
import com.sadri.designsystem.component.Loading
import com.sadri.designsystem.theme.space
import com.sadri.model.UserEntity

@Composable
internal fun ProfileRoute(
  viewModel: ProfileViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()
  val activity = (LocalContext.current as? Activity)


  val logout: () -> Unit = remember {
    {
      viewModel.logout()
      activity?.finish()
    }
  }

  ProfileScreen(
    uiState = uiState.value,
    modifier = modifier,
    retry = viewModel::retry,
    logout = logout
  )
}

@Composable
internal fun ProfileScreen(
  uiState: ProfileUiState,
  modifier: Modifier = Modifier,
  retry: () -> Unit,
  logout: () -> Unit
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(MaterialTheme.space.medium)
  ) {

    when (uiState) {
      ProfileUiState.Loading -> {
        Loading()
      }
      is ProfileUiState.Success -> {
        ProfileSection(
          userEntity = uiState.userEntity,
          logout = logout
        )
      }
      is ProfileUiState.Error -> {
        Error(message = requireNotNull(uiState.error.message)) {
          retry.invoke()
        }
      }
    }
  }
}

@Composable
private fun BoxScope.ProfileSection(
  userEntity: UserEntity,
  logout: () -> Unit
) {
  Column(
    modifier = Modifier
      .align(Alignment.Center)
      .padding(MaterialTheme.space.medium),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row {
      Icon(imageVector = Icons.Rounded.Info, contentDescription = "name")
      Text(
        text = userEntity.name,
        color = MaterialTheme.colorScheme.outline
      )
    }
    Spacer(modifier = Modifier.height(MaterialTheme.space.medium))
    Row {
      Icon(imageVector = Icons.Rounded.Create, contentDescription = "username")
      Text(
        text = userEntity.username,
        color = MaterialTheme.colorScheme.outline
      )
    }
    Spacer(modifier = Modifier.height(MaterialTheme.space.medium))
    Row {
      Icon(imageVector = Icons.Rounded.Email, contentDescription = "email")
      Text(
        text = userEntity.email,
        color = MaterialTheme.colorScheme.outline
      )
    }
    Spacer(modifier = Modifier.height(MaterialTheme.space.medium))
    Row {
      Icon(imageVector = Icons.Rounded.Phone, contentDescription = "phone")
      Text(
        text = userEntity.phone,
        color = MaterialTheme.colorScheme.outline
      )
    }
    Spacer(modifier = Modifier.height(MaterialTheme.space.medium))
    OutlinedButton(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        logout.invoke()
      }
    ) {
      Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = "logout")
      Text(
        "Logout",
        color = MaterialTheme.colorScheme.outline
      )
    }
  }
}