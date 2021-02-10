package com.dionep.ribscage.backend

import com.dionep.ribscage.backend.routes.auth
import com.google.gson.GsonBuilder
import io.ktor.application.*
import io.ktor.auth.*
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
    install(Routing) {
        auth()
    }

    install(Authentication) {
        basic {
            realm
        }
    }
}