package com.sadri.common.utils

import com.sadri.model.LocalException
import com.sadri.model.AppException
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.UnknownHostException

fun Throwable.toLocalException(): LocalException {
  return LocalException(
    message = message,
    cause = cause
  )
}

fun Throwable.toNetworkException(): AppException {
  return when (this) {
    is AppException -> this
    is UnknownHostException -> AppException.Disconnected(cause = this)
    is IOException -> AppException.IO(cause = this)
    is HttpException -> this.toHttpException()
    else -> AppException.Unexpected
  }
}

private fun Throwable.toHttpException(): AppException {
  check(this is HttpException) { "$this is not a HttpException" }
  return when {
    isInternalError() -> AppException.Http(cause = this)
    else -> AppException.Unexpected
  }
}

private fun HttpException.isInternalError(): Boolean {
  return code() == HTTP_INTERNAL_ERROR
}