package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Foods
import com.github.cawtoz.dineapi.modules.models.Food

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
)

