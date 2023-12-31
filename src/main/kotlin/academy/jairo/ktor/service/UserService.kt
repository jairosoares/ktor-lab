package academy.jairo.ktor.service

import academy.jairo.ktor.adapter.UserSqlRepository
import academy.jairo.ktor.domain.user.relational.UserDTO
import academy.jairo.ktor.domain.user.relational.UserTB

class UserService (private val userSqlRepository: UserSqlRepository) {

    suspend fun create(user: UserDTO): UserTB {
        return userSqlRepository.create(user)
    }

    suspend fun findById(id: Long): UserTB? {
        return userSqlRepository.findById(id)
    }

    suspend fun update(id: Long, user: UserDTO): Boolean {
        return userSqlRepository.update(id, user)
    }

    suspend fun delete(id: Long): Boolean {
        return userSqlRepository.delete(id)
    }

    suspend fun findAll(): List<UserTB> {
        return userSqlRepository.findAll()
    }

}