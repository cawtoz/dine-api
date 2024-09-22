package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val name: String,
    val description: String,
    val price: Double,
    val id: Int = 0,
    var menuId: Int = 0,
)