package academy.jairo.ktor.adapter.factory

import org.jetbrains.exposed.sql.Database

interface DatabaseFactory {
    fun create(): Database
}