package academy.jairo.ktor

import academy.jairo.ktor.adapter.repositoy.MongoDBAtlasDatabase
import academy.jairo.ktor.adapter.repositoy.PostgreSQLDatabase
import academy.jairo.ktor.plugins.configureException
import academy.jairo.ktor.plugins.configureRouting
import academy.jairo.ktor.plugins.configureSerialization
import academy.jairo.ktor.routes.configureUseRouting
import academy.jairo.ktor.routes.configureUserMongoRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
        watchPaths = listOf("classes")
    ).start(wait = true)
}

fun Application.module() {

    install(DefaultHeaders) {
        header("My-Header", "My Value")
    }
    configureException()
    configureSerialization()
    configureRouting()

    val database = PostgreSQLDatabase().create()
    configureUseRouting(database)

    //val database = MongoDBAtlasDatabase().create()
    //configureUserMongoRouting(database)

}
