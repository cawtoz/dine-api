package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val name: String,
    val menus: MutableList<Menu> = mutableListOf(),
    val id: Int = 0
) {

    fun addMenu(vararg menus: Menu): Restaurant {
        this.menus.addAll(menus)
        return this
    }

}