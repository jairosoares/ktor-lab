package academy.jairo.ktor.config

import io.ktor.server.application.*
import io.ktor.server.config.*

object EnvConfig {

    private lateinit var config: ApplicationConfig

    fun init(application: Application) {
        config = application.environment.config
        println("Configuração do EnvConfig: $config")
    }

    fun jwtIssuer(): String {
        return config.propertyOrNull("jwt.issuer")?.getString() ?: ""
    }

    fun jwtRealm(): String {
        val realm = config.propertyOrNull("jwt.realm")?.getString() ?: ""
        println("olha o realm ai> $realm")
        return realm
    }
}