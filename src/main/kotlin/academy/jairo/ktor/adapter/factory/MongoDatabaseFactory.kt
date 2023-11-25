package academy.jairo.ktor.adapter.factory

import com.mongodb.kotlin.client.coroutine.MongoDatabase

interface MongoDatabaseFactory {
    fun create(): MongoDatabase
}