package academy.jairo.ktor.usercase

import academy.jairo.ktor.adapter.repositoy.MongoDBAtlasDatabase
import academy.jairo.ktor.adapter.repositoy.user.UserMongoRepository
import academy.jairo.ktor.domain.user.document.UserMongo
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {

    val database = MongoDBAtlasDatabase()

    runBlocking {
        val userRepository = UserMongoRepository(database.create())
        val user: UserMongo = userRepository.create(
            UserMongo.builder()
                .name("Bob Esponja")
                .age(20)
            .build())
        println("Success created: $user")
        readUsers(database.create())
    }
}

suspend fun addUser(database: MongoDatabase) {
    val userMongo = UserMongo.builder()
        .name("Bob Esponja")
        .age(53)
        .build()

    val collection = database.getCollection<UserMongo>("user")
    collection.insertOne(userMongo).also {
        println("Success inserted id: ${it.insertedId}")
    }
}

suspend fun addManyUser(database: MongoDatabase) {
    val lilaCat = UserMongo.builder()
        .name("Lila Cat")
        .age(17)
        .build()

    val billCat = UserMongo.builder()
        .name("Bill Cat")
        .age(5)
        .build()

    val collection = database.getCollection<UserMongo>("user")
    collection.insertMany(listOf(lilaCat, billCat)).also {
        println("Success inserted ids: ${it.insertedIds}")
    }

}

suspend fun readUsers(database: MongoDatabase) {
    val collection = database.getCollection<UserMongo>("user")

    var query = Filters.or(
        listOf(
            Filters.eq("name", "Jairo Soares")
        )
    )
    collection.find<UserMongo>(query).collect() {
        println("Founded: $it")
    }

}

suspend fun updateUser(database: MongoDatabase) {
    val collection = database.getCollection<UserMongo>("user")

    val query = Filters.eq(UserMongo::name.name, "Bill Cat")
    val updateSet = Updates.set(UserMongo::name.name, "Bill Cat II")

    collection.updateOne(filter = query, update = updateSet).also {
        println("Updated docs ${it.matchedCount} and updates docs ${it.modifiedCount}"  )
    }

}

suspend fun deleteUser(database: MongoDatabase) {
    val collection = database.getCollection<UserMongo>("user")

    val query = Filters.eq(UserMongo::name.name, "Bill Cat II")

    collection.deleteMany(filter = query).also {
        println("Deleted count ${it.deletedCount}"  )
    }

}