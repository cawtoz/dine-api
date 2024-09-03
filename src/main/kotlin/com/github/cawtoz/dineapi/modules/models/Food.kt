package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: Int = 0,
    val menuId: Int,
    val name: String,
    val description: String,
    val price: Double
)