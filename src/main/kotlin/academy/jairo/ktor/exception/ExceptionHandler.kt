package academy.jairo.ktor.exception

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

object ExceptionHandler {

    suspend fun handle(
        call: ApplicationCall,
        cause: Throwable,
        developmentMode:Boolean
    ) {
        when (cause) {
            is EntityNotFoundException -> {
                call.respond(
                    HttpStatusCode.NotFound,
                    ExceptionResponse(
                        "${HttpStatusCode.NotFound.value} - ${HttpStatusCode.NotFound.description}",
                        cause.message ?: cause.toString())
                )
            }
            is ParameterException -> {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ExceptionResponse("${HttpStatusCode.BadRequest.value} - ${HttpStatusCode.BadRequest.description}",
                        cause.message ?: cause.toString(),)
                )
            }
            is BusinessException -> {
                call.respond(
                    HttpStatusCode.PreconditionFailed,
                    ExceptionResponse(cause.messageCode.value.toString(),
                        cause.message ?: cause.toString())
                )
            }
            else -> {
                if (developmentMode) {
                    cause.stackTrace.forEach { println(it) }
                    call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
                } else {
                    // Production, only minimal info.
                    call.respondText(text = "Internal Error", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

}
