package com.sadri.common.utils

import android.content.Context
import com.sadri.model.LocalException
import com.sadri.model.NetworkException
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

fun Throwable.toNetworkException(): NetworkException {
  return when (this) {
    is NetworkException -> this
    is UnknownHostException -> NetworkException.Disconnected(cause = this)
    is IOException -> NetworkException.IO(cause = this)
    is HttpException -> this.toHttpException()
    else -> NetworkException.Unexpected
  }
}

private fun Throwable.toHttpException(): NetworkException {
  check(this is HttpException) { "$this is not a HttpException" }
  return when {
    isInternalError() -> NetworkException.Http(cause = this)
    else -> NetworkException.Unexpected
  }
}

private fun HttpException.isInternalError(): Boolean {
  return code() == HTTP_INTERNAL_ERROR
}