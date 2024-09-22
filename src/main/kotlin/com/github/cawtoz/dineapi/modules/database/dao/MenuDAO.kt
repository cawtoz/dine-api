package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Menus
import com.github.cawtoz.dineapi.modules.models.Menu

class MenuDAO: GenericDAO<Menu>(
    "Menu",
    Menus,
    { menu ->
        this[Menus.name] = menu.name
        this[Menus.restaurantId] = menu.restaurantId
    },
    { row ->
        val menuId = row[Menus.id].value
        Menu(
            menuId,
            row[Menus.name],
            row[Menus.restaurantId],
            FoodDAO().selectAll().filter { it.menuId == menuId }
        )
    },
    { menu, menuId ->
        menu.foods.forEach { food ->
            food.menuId = menuId
            FoodDAO().insert(food)
        }
    }
) {

    override fun deleteById(id: Int): Boolean {
        FoodDAO().deleteByMenuId(id)
        return super.deleteById(id)
    }

}

