package com.sadri.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadri.designsystem.component.SimpleTextField
import com.sadri.designsystem.theme.space

@Composable
internal fun LoginRoute(
  viewModel: LoginViewModel = hiltViewModel(),
  modifier: Modifier = Modifier,
  onSubmitSuccessfully: () -> Unit,
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()

  LoginScreen(
    uiState = uiState.value,
    login = viewModel::login,
    modifier = modifier,
    onSubmitSuccessfully = onSubmitSuccessfully
  )
}

@Composable
internal fun LoginScreen(
  username: String = "",
  uiState: LoginUiState,
  login: (String) -> Unit,
  onSubmitSuccessfully: () -> Unit,
  modifier: Modifier = Modifier
) {
  val onSubmit by rememberUpdatedState(onSubmitSuccessfully)
  val focusRequester = remember { FocusRequester() }
  val textFieldValue = rememberSaveable(username, stateSaver = TextFieldValue.Saver) {
    mutableStateOf(
      TextFieldValue(
        text = username,
        selection = TextRange(username.length)
      )
    )
  }
  val snackbarHostState = remember { SnackbarHostState() }

  Column(
    modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SimpleTextField(
      value = textFieldValue.value,
      onValueChange = { textFieldValue.value = it },
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = MaterialTheme.space.large)
        .focusRequester(focusRequester),
    )
    OutlinedButton(
      onClick = { login(textFieldValue.value.text) }
    ) {
      Text("Submit")
    }

    if (uiState !is LoginUiState.Error) {
      Loading()
    }

    when (uiState) {
      LoginUiState.Error -> {
        LaunchedEffect(uiState){
          snackbarHostState.showSnackbar("Something went wrong!")
        }
      }
      LoginUiState.Loading -> {
        // no-op already handled above
      }
      LoginUiState.Nothing -> {
        // no-op
      }
      is LoginUiState.Success -> {
        LaunchedEffect(Unit) {
          onSubmit()
        }
      }
    }

  }

}

@Composable
private fun Loading() {
  CircularProgressIndicator(
    modifier = Modifier.size(MaterialTheme.space.xLarge),
    color = MaterialTheme.colorScheme.tertiary
  )
}