package com.github.cawtoz.dineapi

import com.github.cawtoz.dineapi.modules.database.DatabaseConfig
import com.github.cawtoz.dineapi.modules.routes.routesModule
import io.github.cdimascio.dotenv.dotenv
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main() {

    val port = dotenv()["SERVER_PORT"].toInt()

    embeddedServer(Netty, port = port) {

        install(ContentNegotiation) {
            json(Json { prettyPrint = true })
        }

    }.start(wait = true)

}