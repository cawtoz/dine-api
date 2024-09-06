package com.github.cawtoz.dineapi.modules.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Foods: IntIdTable() {
    val name = varchar("name", 30)
    val description = varchar("description", 120)
    val price = double("price")
    val menuId = integer("menu_id").references(Menus.id)
}