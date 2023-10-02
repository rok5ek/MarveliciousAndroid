package rokpetk.marvelicious.app.android.screens

import rokpetk.marvelicious.app.domain.models.HeroModel

data class HomeState(
    val items: List<HeroModel> = listOf()
)