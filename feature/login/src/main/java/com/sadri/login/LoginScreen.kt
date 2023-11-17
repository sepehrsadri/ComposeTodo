package com.sadri.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadri.designsystem.component.SimpleTextField
import com.sadri.designsystem.theme.space

@Composable
internal fun LoginRoute(
  viewModel: LoginViewModel = hiltViewModel(),
  modifier: Modifier = Modifier,
  onSubmitSuccessfully: () -> Unit,
  onShowSnackbar: suspend (String, String?) -> Boolean,
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()

  LoginScreen(
    uiState = uiState.value,
    login = viewModel::login,
    modifier = modifier,
    onSubmitSuccessfully = onSubmitSuccessfully,
    onShowSnackbar = onShowSnackbar
  )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun LoginScreen(
  username: String = "",
  uiState: LoginUiState,
  login: (String) -> Unit,
  onSubmitSuccessfully: () -> Unit,
  modifier: Modifier = Modifier,
  onShowSnackbar: suspend (String, String?) -> Boolean,
) {
  val onSubmit by rememberUpdatedState(onSubmitSuccessfully)
  val keyboardController = LocalSoftwareKeyboardController.current
  val textFieldValue = rememberSaveable(username, stateSaver = TextFieldValue.Saver) {
    mutableStateOf(
      TextFieldValue(
        text = username,
        selection = TextRange(username.length)
      )
    )
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .background(color = MaterialTheme.colorScheme.background)
      .padding(MaterialTheme.space.medium),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_login),
      contentDescription = "Login icon"
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      SimpleTextField(
        value = textFieldValue.value,
        onValueChange = { textFieldValue.value = it },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = MaterialTheme.space.large),
        label = { Text(text = "Username") },
        keyboardActions = KeyboardActions(
          onDone = {
            keyboardController?.hide()
            login(textFieldValue.value.text)
          })
      )
      Spacer(modifier = Modifier.height(MaterialTheme.space.medium))
      OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          keyboardController?.hide()
          login(textFieldValue.value.text)
        }
      ) {
        Text(
          "Submit",
          color = MaterialTheme.colorScheme.outline
        )
      }
      Spacer(modifier = Modifier.height(MaterialTheme.space.xLarge))
      when (uiState) {
        is LoginUiState.Error -> {
          LaunchedEffect(uiState) {
            onShowSnackbar(requireNotNull(uiState.error.message), "")
          }
        }
        LoginUiState.Loading -> {
          Loading()
        }
        LoginUiState.Nothing -> {
          // no-op
        }
        is LoginUiState.Success -> {
          Loading()
          LaunchedEffect(Unit) {
            onSubmit.invoke()
          }
        }
      }
    }
  }
}

@Composable
private fun Loading() {
  CircularProgressIndicator(
    modifier = Modifier
      .size(MaterialTheme.space.xLarge),
    color = MaterialTheme.colorScheme.primary
  )
}