package rokpetk.marvelicious.app.android.screens.herodetails

import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.SeriesModel

data class HeroDetailsState(
    val name: String = "",
    val image: String = "",
    val comics: List<ComicModel> = listOf(),
    val events: List<EventModel> = listOf(),
    val series: List<SeriesModel> = listOf()
)