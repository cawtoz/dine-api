package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val id: Int = 0,
    val name: String,
    val foods: List<Food> = emptyList()
)