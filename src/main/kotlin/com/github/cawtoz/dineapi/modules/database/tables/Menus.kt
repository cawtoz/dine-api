package com.github.cawtoz.dineapi.modules.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Menus: IntIdTable() {
    val name = varchar("name", 30)
}