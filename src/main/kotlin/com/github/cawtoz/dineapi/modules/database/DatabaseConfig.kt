package com.github.cawtoz.dineapi.modules.database

import com.github.cawtoz.dineapi.modules.database.tables.Foods
import com.github.cawtoz.dineapi.modules.database.tables.Menus
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {

    fun initDatabase() {

        val dotenv = dotenv()

        Database.connect(
            dotenv["DATABASE_URL"],
            dotenv["DATABASE_DRIVER"],
            dotenv["DATABASE_USER"],
            dotenv["DATABASE_PASSWORD"]
        )

        transaction {
            SchemaUtils.create(Menus)
            SchemaUtils.create(Foods)
        }

    }

}