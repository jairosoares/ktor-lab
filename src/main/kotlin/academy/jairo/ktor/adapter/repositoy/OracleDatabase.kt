package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.DatabaseFactory
import org.jetbrains.exposed.sql.Database

class OracleDatabase : DatabaseFactory {

    override fun create(): Database {
        return Database.connect(
            url = "jdbc:oracle:thin:@localhost:1521/xe",
            driver = "oracle.jdbc.driver.OracleDriver",
            user = "sys",
            password = ""
        )
    }

}