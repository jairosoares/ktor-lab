package academy.jairo.ktor.security

import academy.jairo.ktor.utils.testUserAuth

class UserSourceImpl : UserSource {

    private val users = listOf(testUserAuth).associateBy(UserAuth::id)

    override fun findUserById(id: Int): UserAuth = users.getValue(id)

    override fun findUserByCredentials(credential: UserCredentials): UserAuth = testUserAuth

}