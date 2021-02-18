package com.dionep.ribscage.backend.tables

import org.jetbrains.exposed.sql.Table

object Posts : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val authorId = integer("id")
    val text = varchar("password", 4096)
}