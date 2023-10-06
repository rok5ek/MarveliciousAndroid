package rokpetk.marvelicious.app.domain.reponses

sealed interface ApiResponse<out T : Any> {

    data class Success<T : Any>(val result: T) : ApiResponse<T>

    data class Error<T : Any>(
        val code: Int,
        val message: String?,
        val error: String?
    ) : ApiResponse<T>

    data class Exception(val error: Throwable) : ApiResponse<Nothing>
}