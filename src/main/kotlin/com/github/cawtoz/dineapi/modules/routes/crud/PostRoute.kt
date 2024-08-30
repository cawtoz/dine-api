package com.github.cawtoz.dineapi.modules.routes.crud

import com.github.cawtoz.dineapi.modules.database.dao.GenericDAO
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

inline fun <reified T : Any> Route.postRoute(path: String, dao: GenericDAO<T>) {

    val entityName = dao.entityName

    post(path) {

        val entity = call.receive<T>()

        if (dao.insert(entity)) {
            call.respond(HttpStatusCode.Created, "$entityName created successfully")
        } else {
            call.respond(HttpStatusCode.InternalServerError, "Failed to create $entityName")
        }

    }

}