package academy.jairo.ktor

import academy.jairo.ktor.adapter.repositoy.PostgreSQLDatabase
import academy.jairo.ktor.config.EnvConfig
import academy.jairo.ktor.plugins.configureAuthentication
import academy.jairo.ktor.plugins.configureException
import academy.jairo.ktor.plugins.configureRouting
import academy.jairo.ktor.plugins.configureSerialization
import academy.jairo.ktor.routes.configureUseRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    install(DefaultHeaders) {
        header("My-Header", "My Value")
    }
    EnvConfig.init(this)
    configureAuthentication()
    configureException()
    configureSerialization()
    configureRouting()

    val database = PostgreSQLDatabase().create()
    configureUseRouting(database)

    //val database = MongoDBAtlasDatabase().create()
    //configureUserMongoRouting(database)

}
