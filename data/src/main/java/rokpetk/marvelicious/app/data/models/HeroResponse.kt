package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("thumbnail") private val thumbnail: HeroThumbnailResponse,
) {
    val image: String
        get() = "${thumbnail.path}.${thumbnail.extension}"
}

@Serializable
data class HeroThumbnailResponse(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String,
)