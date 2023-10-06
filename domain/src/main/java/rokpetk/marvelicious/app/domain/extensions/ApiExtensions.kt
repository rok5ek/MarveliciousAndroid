package rokpetk.marvelicious.app.domain.extensions

import retrofit2.HttpException
import retrofit2.Response
import rokpetk.marvelicious.app.domain.reponses.ApiResponse

suspend inline fun <reified From : Any, reified To : Any> handleApi(
    mapper: (From) -> To,
    execute: suspend () -> Response<From>
): ApiResponse<To> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResponse.Success(mapper(body))
        } else {
            ApiResponse.Error(
                code = response.code(),
                message = response.message(),
                error = response.errorBody().getErrorObject()
            )
        }
    } catch (e: HttpException) {
        ApiResponse.Error(code = e.code(), message = e.message(), error = null)
    } catch (e: Throwable) {
        ApiResponse.Exception(e)
    }
}