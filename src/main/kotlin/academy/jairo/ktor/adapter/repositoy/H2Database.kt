package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.DatabaseFactory
import org.jetbrains.exposed.sql.Database

class H2Database : DatabaseFactory {

    override fun create(): Database {
        return Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        )
    }

}