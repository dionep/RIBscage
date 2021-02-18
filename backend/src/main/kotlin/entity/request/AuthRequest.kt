package com.dionep.ribscage.backend.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val name: String,
    val password: String
)