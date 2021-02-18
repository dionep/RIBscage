package com.dionep.ribscage.backend.routes

import com.dionep.ribscage.backend.entity.User
import com.dionep.ribscage.backend.entity.request.PostRequest
import com.dionep.ribscage.backend.repo.PostsRepository
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.posts() {

    val repo = PostsRepository()

    authenticate {
        route("posts") {

            get {
                val userId = call.principal<User>()
                userId?.let {
                    call.respond(repo.getUserPosts(it.id))
                } ?: call.respond(HttpStatusCode.Unauthorized)
            }

            put {
                val userId = call.principal<User>()
                userId?.let {
                    val postRequest = call.receive<PostRequest>()
                    repo.addPost(it.id, postRequest.text)
                    call.respond(true)
                } ?: call.respond(HttpStatusCode.Unauthorized)
            }

        }

    }

}