package com.dionep.ribscage.backend.routes

import com.dionep.ribscage.backend.entity.LoginResult
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.auth() {

    post("/login") {
        val request = call.receive<LoginResult>()
        if (request.name == "test")
            call.respond(HttpStatusCode.OK)
        else call.respond(HttpStatusCode.Unauthorized)
    }


}