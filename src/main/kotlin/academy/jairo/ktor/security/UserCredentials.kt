package academy.jairo.ktor.security

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val name: String,
    val password: String
)