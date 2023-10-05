package rokpetk.marvelicious.app.domain.models

data class ComicModel(
    val id: Int,
    val name: String,
    val image: String
) : MediaModel {
    override fun name(): String {
        return name
    }

    override fun image(): String {
        return image
    }

}