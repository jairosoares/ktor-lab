package academy.jairo.ktor.routes

import academy.jairo.ktor.exception.ParameterException
import io.ktor.server.application.*

fun ApplicationCall.getParameterId(name: String = "id"): Long {
    val id = parameters[name]?.toLongOrNull()
    return id ?: throw ParameterException("Invalid ID")
}
