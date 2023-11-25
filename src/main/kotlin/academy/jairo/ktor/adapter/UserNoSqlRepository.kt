package academy.jairo.ktor.adapter

import academy.jairo.ktor.domain.user.UserMongo

interface UserNoSqlRepository {

    suspend fun create(userMongo: UserMongo): UserMongo
    suspend fun update(id: String, userMongo: UserMongo) : Boolean
    suspend fun delete(id: String): Boolean
    suspend fun findAll(): List<UserMongo>
    suspend fun findByName(name: String): List<UserMongo>
    suspend fun findById(id: String): UserMongo?

    suspend fun findAllWithError(): List<UserMongo>
}