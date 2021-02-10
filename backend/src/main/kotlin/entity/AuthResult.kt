package com.dionep.ribscage.backend.entity

import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(
    val name: String,
    val password: String
)