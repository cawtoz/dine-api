package com.github.cawtoz.dineapi.modules.database.dao

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

/**
 * A generic Data Access Object (DAO) for performing CRUD operations on a database table.
 *
 * This class provides a set of common database operations for entities represented by a specific table.
 * It requires implementations for inserting new records and converting database rows to entity instances.
 *
 * @param T The type of the entity represented by this DAO.
 * @property entityName The name of the entity that this DAO manages.
 * @property table The database table associated with this DAO.
 * @property statement A lambda function that defines how to insert an entity into the table.
 * @property toEntity A lambda function that converts a database row to an entity instance.
 * @property afterInsert A lambda executed after a successful insertion, receiving the entity and its generated ID.
 */
abstract class GenericDAO<T>(
    val entityName: String,
    val table: IntIdTable,
    private val statement: UpdateBuilder<*>.(T) -> Unit,
    private val toEntity: (ResultRow) -> T,
    private var afterInsert: (T, Int) -> Unit = { _, _ ->}
) {

    /**
     * Retrieves all entities from the database table.
     *
     * @return A list of all entities present in the table.
     */
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

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or null if no entity is found.
     */
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

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     * @return True if the deletion was successful, otherwise false.
     */
    open fun deleteById(id: Int): Boolean {
        return try {
            transaction {
                table.deleteWhere { table.id eq id } > 0
            }
        } catch (e: SQLException) {
            println("Error deleting $entityName by id: ${e.message}")
            false
        }
    }

    /**
     * Inserts a new entity into the database table.
     *
     * @param entity The entity to insert.
     * @return True if the insertion was successful, otherwise false.
     */
    fun insert(entity: T): Boolean {
        return try {
            transaction {
                val id = table.insertAndGetId {
                    statement(it, entity)
                }.value
                afterInsert(entity, id)
                true
            }
        } catch (e: SQLException) {
            println("Error inserting $entityName: ${e.message}")
            false
        }
    }

    /**
     * Updates an existing entity in the database table.
     *
     * @param id The ID of the entity to update.
     * @param entity The entity with the updated values.
     * @return True if the update was successful, otherwise false.
     */
    fun update(id: Int, entity: T): Boolean {
        return try {
            transaction {
                table
                table.update({ table.id eq id }) {
                    statement(it, entity)
                } > 0
            }
        } catch (e: SQLException) {
            println("Error updating $entityName with id $id: ${e.message}")
            false
        }
    }


}
