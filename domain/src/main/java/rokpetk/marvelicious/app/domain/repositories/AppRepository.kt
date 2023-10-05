package rokpetk.marvelicious.app.domain.repositories

import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.HeroModel

interface AppRepository {
    suspend fun getHero(
        heroId: String,
    ): Flow<ApiResponse<HeroModel>>

    suspend fun getHeroes(
        nameStartsWith: String?,
        limit: Int
    ): Flow<ApiResponse<List<HeroModel>>>
}