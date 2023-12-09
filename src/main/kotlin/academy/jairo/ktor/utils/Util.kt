package academy.jairo.ktor.utils

import academy.jairo.ktor.security.UserAuth
import io.ktor.server.application.*
import io.ktor.server.auth.*

val ApplicationCall.userAuth get() = authentication.principal<UserAuth>()

val testUserAuth = UserAuth(1, "Jairo", listOf("Brazil", "Argentina"))