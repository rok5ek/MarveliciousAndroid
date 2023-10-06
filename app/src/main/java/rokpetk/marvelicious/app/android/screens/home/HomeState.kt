package rokpetk.marvelicious.app.android.screens.home

import rokpetk.marvelicious.app.domain.models.HeroModel

data class HomeState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val items: List<HeroModel> = listOf()
)