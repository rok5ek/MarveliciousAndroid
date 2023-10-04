package rokpetk.marvelicious.app.domain.usecases

import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroes @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<Flow<ApiResponse<List<HeroModel>>>, GetHeroes.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<List<HeroModel>>> {
        return repository.getHeroes(
            nameStartsWith = params.nameStartsWith,
            limit = params.limit
        )
    }

    data class Params(
        val nameStartsWith: String? = null,
        val limit: Int = defaultLimit
    )

    companion object {
        const val defaultLimit = 100
    }
}