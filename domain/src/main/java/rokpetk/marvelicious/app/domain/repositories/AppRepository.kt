package rokpetk.marvelicious.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.models.SeriesModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse

interface AppRepository {
    suspend fun getHero(
        heroId: String,
    ): Flow<ApiResponse<HeroModel, ErrorResponse>>

    suspend fun getHeroes(
        nameStartsWith: String?,
        limit: Int
    ): Flow<ApiResponse<List<HeroModel>, ErrorResponse>>

    suspend fun getHeroComics(
        heroId: String,
    ): Flow<ApiResponse<List<ComicModel>, ErrorResponse>>

    suspend fun getHeroSeries(
        heroId: String,
    ): Flow<ApiResponse<List<SeriesModel>, ErrorResponse>>

    suspend fun getHeroEvents(
        heroId: String,
    ): Flow<ApiResponse<List<EventModel>, ErrorResponse>>
}