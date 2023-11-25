package academy.jairo.ktor.domain.user

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class UserMongoDTO (
    val name: String,
    val age: Int
)

fun UserMongo.toDTO(): UserMongoDTO {
    return UserMongoDTO(name = this.name, age = this.age)
}

fun UserMongoDTO.toUser(): UserMongo {
    return UserMongo(id = ObjectId(), name = this.name, age = this.age)
}