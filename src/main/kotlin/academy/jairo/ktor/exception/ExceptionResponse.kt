package academy.jairo.ktor.exception

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse (
    val status:String,
    val message: String
)