package rokpetk.marvelicious.app.domain.models

data class HeroDetailsModel(
    val id: Int,
    val name: String,
    val image: String,
    val events: List<EventModel>,
    val series: List<SeriesModel>,
    val comics: List<ComicModel>,
)