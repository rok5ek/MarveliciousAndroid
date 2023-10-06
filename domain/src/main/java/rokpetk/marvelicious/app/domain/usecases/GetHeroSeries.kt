package rokpetk.marvelicious.app.domain.usecases

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.SeriesModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroSeries @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<Flow<ApiResponse<List<SeriesModel>>>, GetHeroSeries.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<List<SeriesModel>>> {
        return repository.getHeroSeries(
            heroId = params.heroId,
        )
    }

    data class Params(
        val heroId: String
    )
}