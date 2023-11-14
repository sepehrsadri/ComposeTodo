package com.sadri.data.model

import com.sadri.model.UserEntity
import com.sadri.network.model.UserResponse

fun UserResponse.asEntity() = UserEntity(
  id = id,
  name = name,
  username = username,
  email = email,
  phone = phone
)