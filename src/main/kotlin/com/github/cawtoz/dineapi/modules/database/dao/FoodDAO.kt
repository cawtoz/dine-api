package com.github.cawtoz.dineapi.modules.database.dao

import com.github.cawtoz.dineapi.modules.database.tables.Foods
import com.github.cawtoz.dineapi.modules.models.Food

class FoodDAO: GenericDAO<Food>(
    "Food",
    Foods,
    { statement, food ->
        statement[Foods.name] = food.name
        statement[Foods.description] = food.description
        statement[Foods.price] = food.price
    },
    { row ->
        Food(
            row[Foods.id].value,
            row[Foods.name],
            row[Foods.description],
            row[Foods.price]
        )
    }
)

