package rokpetk.marvelicious.app.domain.usecases

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.ComicModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroComics @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<Flow<ApiResponse<List<ComicModel>>>, GetHeroComics.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<List<ComicModel>>> {
        return repository.getHeroComics(
            heroId = params.heroId,
        )
    }

    data class Params(
        val heroId: String
    )
}