package academy.jairo.ktor.plugins

import academy.jairo.ktor.config.EnvConfig
import academy.jairo.ktor.config.JwtConfig
import academy.jairo.ktor.request.LoginResponse
import academy.jairo.ktor.security.*
import academy.jairo.ktor.utils.userAuth
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAuthentication() {

    val username = System.getenv("AUTH_USERNAME")
    val password = System.getenv("AUTH_PASSWORD")

    val userSource: UserSource = UserSourceImpl()

    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = EnvConfig.jwtRealm()
            validate {
                it.payload.getClaim("id").asInt()?.let(userSource::findUserById)
            }
        }
    }

    install(Routing) {

        /**
         * Safe route
         */
        authenticate {
            route("/security") {
                get {
                    val user = call.userAuth!!
                    call.respond(user.countries)
                }
            }
        }

        /**
         * Public, used to obtain JWTs
         */
        post("/login") {
            val credentials = call.receive<UserCredentials>()
            val user = userSource.findUserByCredentials(credentials)
            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid Credentials")
            } else if (authenticateUser(credentials, username = username, password = password)) {
                val token = JwtConfig.createToken(user)
                val response = LoginResponse(
                    token = token,
                    message = "Success login!",
                    userAuth = user
                )
                call.respond(response)
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid Credentials")
            }

        }

    }

}

private fun authenticateUser(user: UserCredentials, username: String, password:String) : Boolean {
    return user.name == username && user.password == password
}