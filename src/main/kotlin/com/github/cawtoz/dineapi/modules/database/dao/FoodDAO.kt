package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Foods
import com.github.cawtoz.dineapi.modules.models.Food
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

class FoodDAO: GenericDAO<Food>(
    "Food",
    Foods,
    { food ->
        this[Foods.menuId] = food.menuId
        this[Foods.name] = food.name
        this[Foods.description] = food.description
        this[Foods.price] = food.price
        println(this)
    },
    { row ->
        Food(
            row[Foods.id].value,
            row[Foods.menuId],
            row[Foods.name],
            row[Foods.description],
            row[Foods.price]
        )
    }
) {

    fun deleteByMenuId(menuId: Int): Boolean {
        return try {
            transaction {
                Foods.deleteWhere { Foods.menuId eq menuId } > 0
            }
        } catch (e: SQLException) {
            println("Error deleting $entityName by id: ${e.message}")
            false
        }
    }

}

