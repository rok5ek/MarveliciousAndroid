package rokpetk.marvelicious.app.domain.usecases

abstract class BaseUseCase<Result, in Params> {
    abstract suspend fun execute(params: Params): Result
}