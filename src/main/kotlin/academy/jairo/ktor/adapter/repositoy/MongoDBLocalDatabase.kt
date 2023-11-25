package academy.jairo.ktor.adapter.repositoy

import academy.jairo.ktor.adapter.factory.MongoDatabaseFactory
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

class MongoDBLocalDatabase() : MongoDatabaseFactory {

    override fun create(): MongoDatabase {
        val client = MongoClient.create(connectionString = "mongodb://localhost:27017")
        return client.getDatabase(databaseName = "casa_inteligente")
    }

}