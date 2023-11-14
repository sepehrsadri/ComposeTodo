package com.sadri.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
  val id: Int,
  val name: String,
  val username: String,
  val email: String,
  val phone: String
)