package rokpetk.marvelicious.app.android.screens.home

import rokpetk.marvelicious.app.domain.models.HeroModel

data class HomeState(
    val searchQuery: String = "",
    val items: List<HeroModel> = listOf()
)