package rokpetk.marvelicious.app.domain.usecases

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.EventModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroEvents @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<Flow<ApiResponse<List<EventModel>>>, GetHeroEvents.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<List<EventModel>>> {
        return repository.getHeroEvents(
            heroId = params.heroId,
        )
    }

    data class Params(
        val heroId: String
    )
}