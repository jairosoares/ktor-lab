package academy.jairo.ktor.adapter

import academy.jairo.ktor.domain.user.relational.UserDTO
import academy.jairo.ktor.domain.user.relational.UserTB

interface UserSqlRepository {

    suspend fun create(user: UserDTO): UserTB
    suspend fun update(id: Long, user: UserDTO) : Boolean
    suspend fun delete(id: Long): Boolean
    suspend fun findAll(): List<UserTB>
    suspend fun findById(id: Long): UserTB?

}