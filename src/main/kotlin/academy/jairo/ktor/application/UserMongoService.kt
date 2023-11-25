package academy.jairo.ktor.application

import academy.jairo.ktor.adapter.UserNoSqlRepository
import academy.jairo.ktor.domain.user.UserMongo


class UserMongoService(private val userNoSqlRepository: UserNoSqlRepository) {

    suspend fun create(userMongo: UserMongo): UserMongo {
        return userNoSqlRepository.create(userMongo)
    }

    suspend fun get(id: String): UserMongo? {
        return userNoSqlRepository.findById(id)
    }

    suspend fun update(id: String, userMongo: UserMongo): Boolean {
        return userNoSqlRepository.update(id, userMongo)
    }

    suspend fun delete(id: String): Boolean {
        return userNoSqlRepository.delete(id)
    }

    suspend fun findAll(): List<UserMongo> {
        return userNoSqlRepository.findAll()
    }

    suspend fun findByName(name: String): List<UserMongo> {
        return userNoSqlRepository.findByName(name)
    }

    suspend fun findAllWithError(): List<UserMongo> {
        return userNoSqlRepository.findAllWithError()
    }

}
