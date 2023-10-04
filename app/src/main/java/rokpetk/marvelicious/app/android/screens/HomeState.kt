package rokpetk.marvelicious.app.android.screens

import rokpetk.marvelicious.app.domain.models.HeroModel

data class HomeState(
    val searchQuery: String = "",
    val items: List<HeroModel> = listOf()
)