package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.DatabaseFactory
import org.jetbrains.exposed.sql.Database
class MySQLDatabase : DatabaseFactory {
    override fun create(): Database {
        return Database.connect(
            url = "jdbc:mysql://localhost/my-db?useSSL=false&serverTimezone=UTC",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = ""
        )
    }

}