package com.dionep.ribscage.backend.tables

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 64)
    val password = varchar("password", 64)
}