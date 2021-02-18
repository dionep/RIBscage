package com.dionep.ribscage.backend.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val text: String
)