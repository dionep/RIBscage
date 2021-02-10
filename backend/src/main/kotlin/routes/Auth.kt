package com.dionep.ribscage.backend.routes

import com.dionep.ribscage.backend.entity.AuthResult
import com.dionep.ribscage.backend.repo.UsersRepository
import config.JWTConfig
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.auth() {

    val repo = UsersRepository()

    post("/login") {
        val request = call.receive<AuthResult>()
        if (request.name == "test")
            call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.Unauthorized)
    }

    post("register") {
        val request = call.receive<AuthResult>()
        repo.makeUser(
            name = request.name,
            pass = request.password
        )?.let { user ->
            call.respond(JWTConfig.makeToken(user))
        } ?: kotlin.run {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

}