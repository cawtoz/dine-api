package com.github.cawtoz.dineapi.modules.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Foods: IntIdTable() {
    val name: Column<String> = varchar("name", 30)
    val description: Column<String> = varchar("description", 120)
    val price: Column<Double> = double("price")
}