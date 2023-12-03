package academy.jairo.ktor.request

import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Response<T> {

    @Serializable
    data class Success<T>(
        val status: String? = null,
        val message: String? = null,
        val data: T?
    ) : Response<T>() {
        data class Builder<T>(
            var status: String? = null,
            var message: String? = null,
            var data: T? = null,
        ) {
            fun statusCode(statusCode: HttpStatusCode) = apply {
                this.status = "${statusCode.value} - ${statusCode.description}"
            }

            fun message(message: String) = apply {
                this.message = message
            }
            fun data(data: T?) = apply {
                this.data = data
            }

            fun build(isStatusCodeCreated: Boolean = false): Success<T> {
                var statusValue = status ?: "${HttpStatusCode.OK.value} - ${HttpStatusCode.OK.description}"
                if (isStatusCodeCreated) {
                    statusValue = status ?: "${HttpStatusCode.Created.value} - ${HttpStatusCode.Created.description}"
                    message("Created with success")
                }
                return Success(statusValue, message, data)
            }

        }
    }

    @Serializable
    data class Error<T>(
        val data: T? = null,
        val message: String,
        @Contextual
        val statusCode: HttpStatusCode = HttpStatusCode.BadRequest
    ) : Response<T>()

}