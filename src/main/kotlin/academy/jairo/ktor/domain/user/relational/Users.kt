package academy.jairo.ktor.domain.user.relational

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id)
}
