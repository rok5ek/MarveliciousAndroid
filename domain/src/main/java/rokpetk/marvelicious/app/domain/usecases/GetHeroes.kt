package rokpetk.marvelicious.app.domain.usecases

import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Inject

class GetHeroes @Inject constructor(
    private val repository: AppRepository
) : BaseUseCase<GetHeroes.Result, GetHeroes.Params>() {

    suspend operator fun invoke(params: Params): Result {
        repository.getHeroes()
        return Result("")
    }

    data class Result(val result: String)

    data class Params(val param: String)
}