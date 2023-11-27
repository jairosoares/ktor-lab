package academy.jairo.ktor.adapter.repositoy.user

import academy.jairo.ktor.adapter.UserSqlRepository
import academy.jairo.ktor.domain.user.relational.UserDTO
import academy.jairo.ktor.domain.user.relational.UserTB
import academy.jairo.ktor.domain.user.relational.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class UsersRepository(private val database: Database): UserSqlRepository {

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    private suspend fun <T> dbQuery(
        block: suspend () -> T
    ): T = newSuspendedTransaction(Dispatchers.IO) {
        block()
    }

    override suspend fun create(user: UserDTO): UserTB {
        val newUser = dbQuery {
            Users.insert {
                it[name] = user.name
                it[age] = user.age
            }.let { UserTB(it[Users.id], it[Users.name], it[Users.age]) }
        }

        return newUser
    }

    override suspend fun findById(id: Long): UserTB? {
        return dbQuery {
            Users.select() { Users.id eq id }
                .map { rowToUserTB(it) }
                .singleOrNull()
        }
    }

    override suspend fun update(id: Long, user: UserDTO) : Boolean {
        return dbQuery {
            val affectedRows = Users.update({ Users.id eq id }) {
                it[name] = user.name
                it[age] = user.age
            }
            affectedRows > 0
        }
    }

    override suspend fun delete(id: Long) : Boolean {
        return dbQuery {
            val affectedRows = Users.deleteWhere { Users.id.eq(id) }
            affectedRows > 0
        }
    }

    override suspend fun findAll(): List<UserTB> = dbQuery {
        Users.selectAll().map { rowToUserTB(it) }
    }

    private fun rowToUserTB(row: ResultRow) = UserTB(
        row[Users.id],
        row[Users.name],
        row[Users.age]
    )
}