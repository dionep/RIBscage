package com.dionep.ribscage.backend.entity

import io.ktor.auth.*

data class User(
    val id: Int,
    val name: String,
    val password: String
) : Principal