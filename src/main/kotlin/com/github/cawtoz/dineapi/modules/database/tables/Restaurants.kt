package com.github.cawtoz.dineapi.modules.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Restaurants: IntIdTable() {
    val name = varchar("name", 120)
}