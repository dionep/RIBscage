package com.dionep.ribscage.backend.routes

import com.dionep.ribscage.backend.entity.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.profile() {

    get("/profile") {
        val principal = call.principal<User>()
        call.respond(principal ?: HttpStatusCode.Unauthorized)
    }

}