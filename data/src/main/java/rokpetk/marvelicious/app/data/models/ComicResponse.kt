package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rokpetk.marvelicious.app.data.mappers.Mapper
import rokpetk.marvelicious.app.domain.models.ComicModel

@Serializable
data class ComicResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val name: String,
    @SerialName("thumbnail") private val thumbnail: ThumbnailResponse,
) : Mapper<ComicResponse, ComicModel> {

    override fun ComicResponse.mapTo(): ComicModel {
        return ComicModel(
            id = id,
            name = name,
            image = thumbnail.image
        )
    }
}

