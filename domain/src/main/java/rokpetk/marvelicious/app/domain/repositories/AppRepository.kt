package rokpetk.marvelicious.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.models.SeriesModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse

interface AppRepository {
    suspend fun getHero(
        heroId: String,
    ): Flow<ApiResponse<HeroModel>>

    suspend fun getHeroes(
        nameStartsWith: String?,
        limit: Int
    ): Flow<ApiResponse<List<HeroModel>>>

    suspend fun getHeroComics(
        heroId: String,
    ): Flow<ApiResponse<List<ComicModel>>>

    suspend fun getHeroSeries(
        heroId: String,
    ): Flow<ApiResponse<List<SeriesModel>>>

    suspend fun getHeroEvents(
        heroId: String,
    ): Flow<ApiResponse<List<EventModel>>>
}