package rokpetk.marvelicious.app.data.repositories

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepository {
    override suspend fun getHeroes(
        nameStartsWith: String?,
        limit: Int
    ): Flow<ApiResponse<List<HeroModel>>> {
        val response = apiService.getHeroes(
            nameStartsWith = nameStartsWith,
            limit = limit
        ).mapSuccess {
            this.data.results.map { item -> item.run { mapTo() } }
        }

        // flowOn works upstream
        return flow { emit(response) }.flowOn(context = dispatcher)
    }
}