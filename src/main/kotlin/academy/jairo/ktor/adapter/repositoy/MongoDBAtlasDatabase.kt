package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.MongoDatabaseFactory
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoDBAtlasDatabase() : MongoDatabaseFactory {

    override fun create(): MongoDatabase {
        val client = MongoClient.create(connectionString = System.getenv("MONGO_URI"))
        return client.getDatabase(databaseName = "my-db")
    }

}