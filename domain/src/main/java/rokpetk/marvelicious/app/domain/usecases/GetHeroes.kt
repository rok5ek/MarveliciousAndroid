package rokpetk.marvelicious.app.domain.usecases

import com.skydoves.sandwich.ApiResponse
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroes @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<ApiResponse<List<HeroModel>>, GetHeroes.Params>() {

    override suspend fun execute(params: Params): ApiResponse<List<HeroModel>> {
        return repository.getHeroes()
    }

    data class Params(val param: String)
}