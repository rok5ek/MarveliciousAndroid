package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rokpetk.marvelicious.app.data.mappers.Mapper
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.SeriesModel

@Serializable
data class SeriesResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val name: String,
    @SerialName("thumbnail") private val thumbnail: ThumbnailResponse,
): Mapper<SeriesResponse, SeriesModel> {

    override fun SeriesResponse.mapTo(): SeriesModel {
        return SeriesModel(
            id = id,
            name = name,
            image = thumbnail.image
        )
    }
}
