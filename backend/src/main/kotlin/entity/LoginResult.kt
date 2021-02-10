package com.dionep.ribscage.backend.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginResult(
    val name: String
)