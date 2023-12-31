package rokpetk.marvelicious.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rokpetk.marvelicious.app.data.mappers.Mapper
import rokpetk.marvelicious.app.domain.models.HeroModel

@Serializable
data class HeroResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("thumbnail") private val thumbnail: ThumbnailResponse,
) : Mapper<HeroResponse, HeroModel> {

    override fun HeroResponse.mapTo(): HeroModel {
        return HeroModel(
            id = id,
            name = name,
            image = thumbnail.image
        )
    }
}
