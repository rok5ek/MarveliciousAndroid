package rokpetk.marvelicious.app.domain.repositories

import com.skydoves.sandwich.ApiResponse
import rokpetk.marvelicious.app.domain.models.HeroModel

interface AppRepository {
    suspend fun getHeroes(): ApiResponse<List<HeroModel>>
}