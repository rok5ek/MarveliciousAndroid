package rokpetk.marvelicious.app.domain.extensions

import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

inline fun <reified T> ResponseBody?.getErrorObject(): T? {
    val errorBody = this?.string() ?: return null
    return Json.decodeFromString(errorBody)
}