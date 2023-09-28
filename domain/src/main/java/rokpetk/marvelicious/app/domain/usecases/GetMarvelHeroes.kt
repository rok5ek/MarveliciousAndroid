package rokpetk.marvelicious.app.domain.usecases

import rokpetk.marvelicious.app.domain.source.remote.AppRepository

class GetMarvelHeroes(
    val repository: AppRepository
): BaseUseCase<GetMarvelHeroes.Result, GetMarvelHeroes.Params>() {

//    suspend operator fun invoke(params: Params): Result {
//
//    }

    data class Result(val result: String)

    data class Params(val param: String)
}