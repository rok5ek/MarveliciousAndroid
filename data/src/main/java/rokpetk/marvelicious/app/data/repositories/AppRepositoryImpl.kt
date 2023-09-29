package rokpetk.marvelicious.app.data.repositories

import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService
) : AppRepository {
    override suspend fun getHeroes() {
        apiService.getHeroes()
    }
}