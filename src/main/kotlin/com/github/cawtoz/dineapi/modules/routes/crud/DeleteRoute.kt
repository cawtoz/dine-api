package com.github.cawtoz.dineapi.modules.routes.crud

import com.github.cawtoz.dineapi.modules.database.dao.GenericDAO
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete

fun <T> Route.deleteRoute(path: String, dao: GenericDAO<T>) {

    val entityName = dao.entityName

    delete("$path/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid $entityName id")
            return@delete
        }

        if (dao.deleteById(id)) {
            call.respond(HttpStatusCode.Created, "$entityName with id $id was deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "$entityName with id $id not exist")
        }

    }

}
