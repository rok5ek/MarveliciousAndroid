package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rokpetk.marvelicious.app.data.mappers.Mapper
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.HeroModel

@Serializable
data class EventResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val name: String,
    @SerialName("thumbnail") private val thumbnail: ThumbnailResponse,
): Mapper<EventResponse, EventModel> {

    override fun EventResponse.mapTo(): EventModel {
        return EventModel(
            id = id,
            name = name,
            image = thumbnail.image
        )
    }
}
