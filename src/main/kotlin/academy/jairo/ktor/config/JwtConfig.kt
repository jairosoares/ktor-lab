package academy.jairo.ktor.config

import academy.jairo.ktor.security.UserAuth
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtConfig {

    private const val VALIDITY_IN_MS = 36_000_00 * 10 // 10 hours

    private val secret = System.getenv("KEY_SECRET")
    private val algorithm = Algorithm.HMAC512(secret)

    /**
     * Check token Validation
     */
    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(EnvConfig.jwtIssuer())
        .build()

    /**
     * Create a token for this combination of User and Account
     */
    fun createToken(userAuth: UserAuth): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(EnvConfig.jwtIssuer())
        .withClaim("id", userAuth.id)
        .withClaim("user_name", userAuth.name)
        .withArrayClaim("countries", userAuth.countries.toTypedArray())
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun getExpiration() = Date(System.currentTimeMillis() + VALIDITY_IN_MS)
}