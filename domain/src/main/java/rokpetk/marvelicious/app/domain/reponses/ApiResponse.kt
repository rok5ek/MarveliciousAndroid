package rokpetk.marvelicious.app.domain.reponses

sealed interface ApiResponse<out T : Any, out E : Any?> {

    data class Success<T : Any, E : Any>(val result: T) : ApiResponse<T, E>

    data class Error<T : Any, E : Any?>(
        val code: Int,
        val message: String?,
        val error: E?
    ) : ApiResponse<T, E>

    data class Exception(val error: Throwable) : ApiResponse<Nothing, Nothing>
}