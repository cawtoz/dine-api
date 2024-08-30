package com.github.cawtoz.dineapi.modules.routes.crud

import com.github.cawtoz.dineapi.modules.database.dao.GenericDAO
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

inline fun <reified T : Any> Application.crudRoutes(path: String, dao: GenericDAO<T>) {

    routing {
        getRoute<T>(path, dao)
        postRoute<T>(path, dao)
        deleteRoute(path, dao)
    }

}