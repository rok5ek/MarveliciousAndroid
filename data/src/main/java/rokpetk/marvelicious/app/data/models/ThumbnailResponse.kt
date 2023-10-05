package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailResponse(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String,
) {
    val image: String
        get() = "${path.replace("http", "https")}.$extension"
}