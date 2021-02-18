package com.dionep.ribscage.backend.entity

data class Post(
    val id: Int,
    val authorId: Int,
    val text: String
)