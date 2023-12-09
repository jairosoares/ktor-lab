package academy.jairo.ktor.request

import academy.jairo.ktor.security.UserAuth
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    val token: String,
    val message: String,
    val userAuth: UserAuth?
)
