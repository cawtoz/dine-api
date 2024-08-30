package com.github.cawtoz.dineapi.modules.routes

import com.github.cawtoz.dineapi.modules.database.dao.FoodDAO
import com.github.cawtoz.dineapi.modules.models.Food
import com.github.cawtoz.dineapi.modules.routes.crud.crudRoutes
import io.ktor.server.application.Application

fun Application.routesModule() {
    crudRoutes<Food>("/food", FoodDAO())
}