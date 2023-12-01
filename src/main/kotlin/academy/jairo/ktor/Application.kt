package academy.jairo.ktor

import academy.jairo.ktor.plugins.configureException
import academy.jairo.ktor.plugins.configureRouting
import academy.jairo.ktor.plugins.configureSerialization
import academy.jairo.ktor.routes.configureUseRouting
import academy.jairo.ktor.routes.configureUserMongoRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

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
    install(DefaultHeaders)

    configureException()
    configureSerialization()
    configureRouting()
    configureUseRouting()
    configureUserMongoRouting()

}
