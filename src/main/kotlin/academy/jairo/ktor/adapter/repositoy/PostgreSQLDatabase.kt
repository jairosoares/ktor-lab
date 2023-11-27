package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.DatabaseFactory
import org.jetbrains.exposed.sql.Database

class PostgreSQLDatabase : DatabaseFactory {

    override fun create(): Database {
        return Database.connect(
            url = "jdbc:postgresql://localhost:5432/my-db",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "admin"
        )
    }

}