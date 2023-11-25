package academy.jairo.ktor.domain.user

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class UserDTO (
    val name: String,
    val age: Int
)

fun UserTB.toDTO(): UserDTO {
    return UserDTO(name = this.name, age = this.age)
}
