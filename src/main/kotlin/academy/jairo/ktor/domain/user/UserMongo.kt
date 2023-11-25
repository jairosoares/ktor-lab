package academy.jairo.ktor.domain.user

import ObjectIdSerializer
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class UserMongo (
    @BsonId
    @Serializable(with = ObjectIdSerializer::class)
    val id: ObjectId?,
    val name: String,
    val age: Int
) {
    companion object {
        fun builder(): UserMongoBuilder {
            return UserMongoBuilder()
        }
    }
}
