package rokpetk.marvelicious.app.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.data.models.ComicWrapperResponse
import rokpetk.marvelicious.app.data.models.EventWrapperResponse
import rokpetk.marvelicious.app.data.models.HeroWrapperResponse
import rokpetk.marvelicious.app.data.models.SeriesWrapperResponse
import rokpetk.marvelicious.app.domain.extensions.handleApi
import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.models.SeriesModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepository {
    override suspend fun getHero(heroId: String): Flow<ApiResponse<HeroModel, ErrorResponse>> {
        val response = handleApi<HeroWrapperResponse, HeroModel, ErrorResponse>(
            mapper = { it.data.results.first().run { mapTo() } }
        ) {
            apiService.getHero(
                heroId = heroId
            )
        }

        // flowOn works upstream
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroes(
        nameStartsWith: String?,
        limit: Int
    ): Flow<ApiResponse<List<HeroModel>, ErrorResponse>> {
        val response = handleApi<HeroWrapperResponse, List<HeroModel>, ErrorResponse>(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) {
            apiService.getHeroes(
                nameStartsWith = nameStartsWith,
                limit = limit
            )
        }

        // flowOn works upstream
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroComics(heroId: String): Flow<ApiResponse<List<ComicModel>, ErrorResponse>> {
        val response = handleApi<ComicWrapperResponse, List<ComicModel>, ErrorResponse>(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroComics(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroSeries(heroId: String): Flow<ApiResponse<List<SeriesModel>, ErrorResponse>> {
        val response = handleApi<SeriesWrapperResponse, List<SeriesModel>, ErrorResponse>(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroSeries(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroEvents(heroId: String): Flow<ApiResponse<List<EventModel>, ErrorResponse>> {
        val response = handleApi<EventWrapperResponse, List<EventModel>, ErrorResponse>(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroEvents(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }
}