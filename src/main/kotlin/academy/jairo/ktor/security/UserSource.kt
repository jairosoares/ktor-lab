package academy.jairo.ktor.security

interface UserSource {

    fun findUserById(id: Int): UserAuth

    fun findUserByCredentials(credential: UserCredentials): UserAuth

}