package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventWrapperResponse(
    @SerialName("data") val data: EventDataResponse,
)

@Serializable
data class EventDataResponse(
    @SerialName("results") val results: List<EventResponse>,
)
