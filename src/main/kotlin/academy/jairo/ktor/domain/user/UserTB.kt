package academy.jairo.ktor.domain.user

import kotlinx.serialization.Serializable

@Serializable
data class UserTB(
    val id: Long,
    val name: String,
    val age: Int
)