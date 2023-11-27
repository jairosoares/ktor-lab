package academy.jairo.ktor.adapter.repositoy.user

import academy.jairo.ktor.adapter.UserNoSqlRepository
import academy.jairo.ktor.domain.user.document.UserMongo
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document
import org.bson.types.ObjectId

class UserMongoRepository (private val database: MongoDatabase) : UserNoSqlRepository {

    private val collection = database.getCollection<UserMongo> ("user")

    override suspend fun create(userMongo: UserMongo): UserMongo {
        val result = collection.insertOne(userMongo).also {
            println("Success inserted id: ${it.insertedId}")
        }
        val insertedId = result.insertedId?.asObjectId()?.value
        return userMongo.copy(id = insertedId)
    }

    override suspend fun findById(id: String): UserMongo? {
        val user = collection.find(Filters.eq("_id", ObjectId(id))).firstOrNull()
        return user
    }

    override suspend fun findByName(name: String): List<UserMongo> {
        var query = Filters.or(
            listOf(
                Filters.eq("name", name)
            )
        )
        var userMongos = collection.find<UserMongo>(query).toList()
        return userMongos
    }

    override suspend fun update(id: String, userMongo: UserMongo) : Boolean {
        val updateResult = collection.replaceOne(Filters.eq("id", ObjectId(id)), userMongo)
        return updateResult.matchedCount > 0
    }

    override suspend fun delete(id: String) : Boolean {
        val result: DeleteResult = collection.deleteOne(Filters.eq("_id", ObjectId(id)))
        return result.deletedCount > 0
    }

    /**
     * kotlinx.serialization.SerializationException: Serializer for class 'ObjectId' is not found.
     * Please ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.
     */
    override suspend fun findAllWithError() : List<UserMongo> {
        val documents = database.getCollection<Document> ("user")
        val userDocuments = documents.find().toList()
        return userDocuments.map { document ->
            UserMongo(
                id = document. getObjectId("_id"),
                name = document.getString("name"),
                age = document.getInteger("age")
            )
        }
    }

    override suspend fun findAll() : List<UserMongo> {
        return collection.find().toList()
    }

}