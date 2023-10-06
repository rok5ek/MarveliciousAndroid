package rokpetk.marvelicious.app.domain.extensions

import rokpetk.marvelicious.app.domain.reponses.ApiResponse

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any> result4(
    result1: ApiResponse<T1>,
    result2: ApiResponse<T2>,
    result3: ApiResponse<T3>,
    result4: ApiResponse<T4>,
): ApiResponse<Result4<T1, T2, T3, T4>>? {
    if (result1 is ApiResponse.Error<T1>) {
        return ApiResponse.Error(
            code = result1.code,
            message = result1.message,
            error = result1.error
        )
    } else if (result2 is ApiResponse.Error<T2>) {
        return ApiResponse.Error(
            code = result2.code,
            message = result2.message,
            error = result2.error
        )
    } else if (result3 is ApiResponse.Error<T3>) {
        return ApiResponse.Error(
            code = result3.code,
            message = result3.message,
            error = result3.error
        )
    } else if (result4 is ApiResponse.Error<T4>) {
        return ApiResponse.Error(
            code = result4.code,
            message = result4.message,
            error = result4.error
        )
    } else if (result1 is ApiResponse.Exception) {
        return result1
    } else if (result2 is ApiResponse.Exception) {
        return result2
    } else if (result3 is ApiResponse.Exception) {
        return result3
    } else if (result4 is ApiResponse.Exception) {
        return result4
    } else if (result1 is ApiResponse.Success
        && result2 is ApiResponse.Success
        && result3 is ApiResponse.Success
        && result4 is ApiResponse.Success
    ) {
        return ApiResponse.Success(
            Result4(
                result1 = result1.result,
                result2 = result2.result,
                result3 = result3.result,
                result4 = result4.result,
            )
        )
    } else {
        return null
    }
}

data class Result4<T1, T2, T3, T4>(
    val result1: T1,
    val result2: T2,
    val result3: T3,
    val result4: T4,
)