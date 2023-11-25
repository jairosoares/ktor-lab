package academy.jairo.ktor

import academy.jairo.ktor.routes.configureRouting
import academy.jairo.ktor.routes.configureSerialization
import academy.jairo.ktor.routes.configureUseRouting
import academy.jairo.ktor.routes.configureUserMongoRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

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
    configureSerialization()
    configureRouting()
    configureUseRouting()
    configureUserMongoRouting()
}
