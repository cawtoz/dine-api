package com.github.cawtoz.dineapi.modules.routes

import com.github.cawtoz.dineapi.modules.database.dao.FoodDAO
import com.github.cawtoz.dineapi.modules.database.dao.MenuDAO
import com.github.cawtoz.dineapi.modules.database.dao.RestaurantDAO
import com.github.cawtoz.dineapi.modules.models.Food
import com.github.cawtoz.dineapi.modules.models.Menu
import com.github.cawtoz.dineapi.modules.models.Restaurant
import com.github.cawtoz.dineapi.modules.routes.crud.crudRoutes
import io.ktor.server.application.Application

fun Application.routesModule() {
    crudRoutes<Restaurant>("/restaurant", RestaurantDAO())
    crudRoutes<Menu>("/menu", MenuDAO())
    crudRoutes<Food>("/food", FoodDAO())
}