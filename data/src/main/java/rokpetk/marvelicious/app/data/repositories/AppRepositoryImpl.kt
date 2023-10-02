package rokpetk.marvelicious.app.data.repositories

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService
) : AppRepository {
    override suspend fun getHeroes(): ApiResponse<List<HeroModel>> {
        return apiService.getHeroes().mapSuccess {
            this.data.results.map { item -> item.run { mapTo() } }
        }
    }
}