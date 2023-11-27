package academy.jairo.ktor

import academy.jairo.ktor.routes.configureRouting
import academy.jairo.ktor.routes.configureSerialization
import academy.jairo.ktor.routes.configureUseRouting
import academy.jairo.ktor.routes.configureUserMongoRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json

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
    /*
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    */

    install(StatusPages) {
        exception<Throwable>() { call, cause ->
            call.respondText (text = "500: $cause", status = HttpStatusCode.InternalServerError )
        }
    }

    configureSerialization()
    configureRouting()
    configureUseRouting()
    configureUserMongoRouting()

}
