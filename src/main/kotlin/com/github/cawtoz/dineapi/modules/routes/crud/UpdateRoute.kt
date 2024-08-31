package com.github.cawtoz.dineapi.modules.routes.crud

import com.github.cawtoz.dineapi.modules.database.dao.GenericDAO
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put

inline fun <reified T : Any> Route.updateRoute(path: String, dao: GenericDAO<T>) {

    val entityName = dao.entityName

    put("$path/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()
        val entity = call.receive<T>()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid $entityName id")
            return@put
        }

        val existingEntity = dao.selectById(id)
        if (existingEntity == null) {
            call.respond(HttpStatusCode.NotFound, "$entityName with id $id not exist")
            return@put
        }

        if (dao.update(id, entity)) {
            call.respond(HttpStatusCode.OK, "$entityName with id $id updated successfully")
        } else {
            call.respond(HttpStatusCode.InternalServerError, "Failed to update $entityName with id $id")
        }

    }

}
