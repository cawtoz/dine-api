package com.github.cawtoz.dineapi.modules.models

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val id: Int = 0,
    val name: String,
    val menu: List<Menu> = emptyList()
)