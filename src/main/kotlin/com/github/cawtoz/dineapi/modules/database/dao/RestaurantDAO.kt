package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Restaurants
import com.github.cawtoz.dineapi.modules.models.Restaurant

class RestaurantDAO: GenericDAO<Restaurant>(
    "Restaurant",
    Restaurants,
    { restaurant -> this[Restaurants.name] = restaurant.name },
    { row ->
        val restaurantId = row[Restaurants.id].value
        Restaurant(
            row[Restaurants.name],
            MenuDAO().selectAll().filter { it.restaurantId == restaurantId }.toMutableList(),
            restaurantId
        )
    },
    { restaurant, restaurantId ->
        restaurant.menus.forEach { menu ->
            menu.restaurantId = restaurantId
            MenuDAO().insert(menu)
        }
    }
)

