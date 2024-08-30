package com.github.cawtoz.dineapi.modules.routes.crud

import com.github.cawtoz.dineapi.modules.database.dao.GenericDAO
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

inline fun <reified T> Route.getRoute(path: String, dao: GenericDAO<T>) {

    val entityName = dao.entityName

    get(path) {
        call.respond(HttpStatusCode.OK, dao.selectAll())
    }

    get("$path/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid $entityName id")
            return@get
        }

        dao.selectById(id)?.let {
            call.respond(HttpStatusCode.OK, it)
        } ?: call.respond(HttpStatusCode.NotFound, "$entityName with id $id not exist")


    }

}
