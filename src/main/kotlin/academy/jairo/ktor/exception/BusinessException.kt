package academy.jairo.ktor.exception

/**
 * Top level exception for all Business exceptions
 */
open class BusinessException
    (val messageCode: MessageCode, message: String?) : RuntimeException(message)