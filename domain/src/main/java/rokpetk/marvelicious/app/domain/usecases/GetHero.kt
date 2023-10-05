package rokpetk.marvelicious.app.domain.usecases

import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHero @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<Flow<ApiResponse<HeroModel>>, GetHero.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<HeroModel>> {
        return repository.getHero(
            heroId = params.id,
        )
    }

    data class Params(
        val id: String
    )
}