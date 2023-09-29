package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(
    @SerialName("data") val data: HeroesDataResponse,
)

@Serializable
data class HeroesDataResponse(
    @SerialName("results") val results: List<HeroResponse>,
)
