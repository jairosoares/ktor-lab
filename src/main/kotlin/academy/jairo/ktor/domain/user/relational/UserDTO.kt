package academy.jairo.ktor.domain.user.relational

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val name: String,
    val age: Int
)

fun UserTB.toDTO(): UserDTO {
    return UserDTO(name = this.name, age = this.age)
}
