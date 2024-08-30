package com.github.cawtoz.dineapi.modules.database.dao

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

abstract class GenericDAO<T>(
    val entityName: String,
    val table: IntIdTable,
    private val insertStatement: (InsertStatement<EntityID<Int>>, T) -> Unit,
    private val toEntity: (ResultRow) -> T
) {

    fun selectAll(): List<T> {
        return try {
            transaction {
                table.selectAll().map { toEntity(it) }
            }
        } catch (e: SQLException) {
            println("Error selecting all ${table.tableName}: ${e.message}")
            emptyList()
        }
    }

    fun selectById(id: Int): T? {
        return try {
            transaction {
                table.selectAll().where { table.id eq id }.map { toEntity(it) }.singleOrNull()
            }
        } catch (e: SQLException) {
            println("Error selecting $entityName by id: ${e.message}")
            null
        }
    }

    fun deleteById(id: Int): Boolean {
        return try {
            transaction {
                table.deleteWhere { table.id eq id } != 0
            }
        } catch (e: SQLException) {
            println("Error deleting $entityName by id: ${e.message}")
            false
        }
    }

    fun insert(entity: T): Boolean {
        return try {
            transaction {
                table.insertAndGetId {
                    insertStatement(it, entity)
                }
                true
            }
        } catch (e: SQLException) {
            println("Error inserting $entityName: ${e.message}")
            false
        }
    }

}
