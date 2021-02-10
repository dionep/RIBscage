package com.dionep.ribscage.backend.repo

import com.dionep.ribscage.backend.dbQuery
import com.dionep.ribscage.backend.entity.User
import com.dionep.ribscage.backend.tables.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UsersRepository {

    fun getUser(id: Int): User? = transaction {
        Users.select { Users.id eq id }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    suspend fun makeUser(name: String, pass: String): User? = dbQuery {
        Users.insert {
            it[Users.name] = name
            it[Users.password] = pass
        }
        Users
            .select { (Users.name eq name) }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    private fun toUser(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
        password = row[Users.password]
    )

}