package com.sadri.model

sealed class NetworkException(
  message: String? = null,
  cause: Throwable? = null
) : LocalException(message, cause) {

  data object Unexpected : NetworkException(
    message = "Something went wrong! = Unexpected code"
  )

  data class IO(
    override val cause: Throwable
  ) : NetworkException(
    message = "Something went wrong! = IO code",
    cause = cause
  )

  class Http(
    override val cause: Throwable
  ) : NetworkException(
    message = "Something went wrong! = Http code",
    cause = cause
  )

  class Disconnected(
    override val cause: Throwable
  ) : NetworkException(
    message = "Something went wrong! = No network connection code",
    cause = cause
  )

  data object UserNotFound : NetworkException(
    message = "Something went wrong! = User not found",
  )

  data object EmptyList : NetworkException(
    message = "Something went wrong! = no todo items found"
  )
}