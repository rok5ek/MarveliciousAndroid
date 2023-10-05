package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesWrapperResponse(
    @SerialName("data") val data: SeriesDataResponse,
)

@Serializable
data class SeriesDataResponse(
    @SerialName("results") val results: List<SeriesResponse>,
)
