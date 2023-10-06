package rokpetk.marvelicious.app.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.domain.extensions.handleApi
import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.models.SeriesModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepository {
    override suspend fun getHero(heroId: String): Flow<ApiResponse<HeroModel>> {
        val response = handleApi(
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
    ): Flow<ApiResponse<List<HeroModel>>> {
        val response = handleApi(
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

    override suspend fun getHeroComics(heroId: String): Flow<ApiResponse<List<ComicModel>>> {
        val response = handleApi(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroComics(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroSeries(heroId: String): Flow<ApiResponse<List<SeriesModel>>> {
        val response = handleApi(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroSeries(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }

    override suspend fun getHeroEvents(heroId: String): Flow<ApiResponse<List<EventModel>>> {
        val response = handleApi(
            mapper = { it.data.results.map { item -> item.run { mapTo() } } }
        ) { apiService.getHeroEvents(heroId = heroId) }
        return flow { emit(response) }.flowOn(context = dispatcher)
    }
}