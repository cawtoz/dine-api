package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Foods
import com.github.cawtoz.dineapi.modules.database.tables.Menus
import com.github.cawtoz.dineapi.modules.models.Food
import com.github.cawtoz.dineapi.modules.models.Menu
import org.jetbrains.exposed.sql.selectAll

class MenuDAO: GenericDAO<Menu>(
    "Menu",
    Menus,
    { menu -> this[Menus.name] = menu.name },
    { row ->

        val menuId = row[Menus.id].value

        val foods = Foods.selectAll().where { Foods.menuId eq menuId }.map {
            Food(
                it[Foods.id].value,
                it[Foods.menuId],
                it[Foods.name],
                it[Foods.description],
                it[Foods.price]
            )
        }

        Menu(menuId, row[Menus.name], foods)

    }
) {

    override fun deleteById(id: Int): Boolean {
        FoodDAO().deleteByMenuId(id)
        return super.deleteById(id)
    }

}

