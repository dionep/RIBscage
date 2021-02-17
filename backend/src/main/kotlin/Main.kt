package com.dionep.ribscage.backend

import com.dionep.ribscage.backend.config.DatabaseConfig
import com.dionep.ribscage.backend.repo.UsersRepository
import com.dionep.ribscage.backend.routes.auth
import com.dionep.ribscage.backend.routes.profile
import com.google.gson.GsonBuilder
import config.JWTConfig
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(Authentication) {
        val repo = UsersRepository()
        jwt {
            verifier(JWTConfig.verifier)
            realm = "ktor.io"
            validate { it.payload.getClaim("id").asInt()?.let(repo::getUser) }
        }
    }

    install(ContentNegotiation) {
        register(
            ContentType.Application.Json,
            GsonConverter(
                GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create()
            )
        )
    }

    install(DefaultHeaders)
    install(CallLogging)

    DatabaseConfig.connect()

    install(Routing) {
        auth()
        profile()
    }
}