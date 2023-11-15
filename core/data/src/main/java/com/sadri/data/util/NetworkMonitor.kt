package com.sadri.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
  val isOnline: Flow<Boolean>
}