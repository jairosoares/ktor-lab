package academy.jairo.ktor.security

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserAuth (
    val id: Int,
    val name: String,
    val countries: List<String>
) : Principal