package com.github.cawtoz.dineapi.modules.database.dao

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
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
 * @property insertStatement A lambda function that defines how to insert an entity into the table.
 * @property toEntity A lambda function that converts a database row to an entity instance.
 */
abstract class GenericDAO<T>(
    val entityName: String,
    val table: IntIdTable,
    private val insertStatement: (InsertStatement<EntityID<Int>>, T) -> Unit,
    private val toEntity: (ResultRow) -> T
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

    /**
     * Inserts a new entity into the database table.
     *
     * @param entity The entity to insert.
     * @return True if the insertion was successful, otherwise false.
     */
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
