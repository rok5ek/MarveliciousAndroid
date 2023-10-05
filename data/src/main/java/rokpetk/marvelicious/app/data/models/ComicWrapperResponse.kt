package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicWrapperResponse(
    @SerialName("data") val data: ComicDataResponse,
)

@Serializable
data class ComicDataResponse(
    @SerialName("results") val results: List<ComicResponse>,
)
