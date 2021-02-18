package com.dionep.ribscage.backend.repo

import com.dionep.ribscage.backend.dbQuery
import com.dionep.ribscage.backend.entity.Post
import com.dionep.ribscage.backend.tables.Posts
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class PostsRepository {

    suspend fun getUserPosts(id: Int): List<Post> = dbQuery {
        Posts.selectAll()
            .mapNotNull(::toPost)
            .filter { it.authorId == id }
    }

    suspend fun addPost(userId: Int, text: String) = dbQuery {
        Posts.insert {
            it[Posts.authorId] = userId
            it[Posts.text] = text
        }
    }

    private fun toPost(row: ResultRow) = Post(
        id = row[Posts.id],
        authorId = row[Posts.authorId],
        text = row[Posts.text]
    )

}