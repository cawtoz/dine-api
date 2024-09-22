package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val name: String,
    val foods: MutableList<Food> = mutableListOf(),
    val id: Int = 0,
    var restaurantId: Int = 0
) {

    fun addFood(vararg foods: Food): Menu {
        this.foods.addAll(foods)
        return this
    }

}