package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroWrapperResponse(
    @SerialName("data") val data: HeroDataResponse,
)

@Serializable
data class HeroDataResponse(
    @SerialName("results") val results: List<HeroResponse>,
)
