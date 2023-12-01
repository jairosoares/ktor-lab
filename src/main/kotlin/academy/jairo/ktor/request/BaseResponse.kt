package academy.jairo.ktor.request

import io.ktor.http.*

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
)  {

    data class Success<T> (
        val data: T? = null,
        val message: String? = null
    ): BaseResponse<T>()

    data class Error<T> (
        val data: T? = null,
        val message: String? = null
    ): BaseResponse<T>()

}