package rokpetk.marvelicious.app.domain.usecases

import kotlinx.coroutines.flow.Flow
import rokpetk.marvelicious.app.domain.extensions.result4
import rokpetk.marvelicious.app.domain.extensions.zipMultiple
import rokpetk.marvelicious.app.domain.models.HeroDetailsModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import javax.inject.Inject

class GetHeroDetails @Inject constructor(
    private val getHero: GetHero,
    private val getHeroComics: GetHeroComics,
    private val getHeroEvents: GetHeroEvents,
    private val getHeroSeries: GetHeroSeries,
) : BaseUseCase<Flow<ApiResponse<HeroDetailsModel, ErrorResponse>>, GetHeroDetails.Params>() {

    override suspend fun execute(params: Params): Flow<ApiResponse<HeroDetailsModel, ErrorResponse>> {
        val heroFlow = getHero.execute(params = GetHero.Params(params.id))
        val heroComicsFlow = getHeroComics.execute(params = GetHeroComics.Params(params.id))
        val heroEventsFlow = getHeroEvents.execute(params = GetHeroEvents.Params(params.id))
        val heroSeriesFlow = getHeroSeries.execute(params = GetHeroSeries.Params(params.id))

        return zipMultiple(
            heroFlow,
            heroComicsFlow,
            heroEventsFlow,
            heroSeriesFlow
        ) { heroResponse, heroComicsResponse, heroEventsResponse, heroSeriesResponse ->
            val response = result4(
                heroResponse,
                heroComicsResponse,
                heroEventsResponse,
                heroSeriesResponse
            )
            when (response) {
                is ApiResponse.Error -> {
                    ApiResponse.Error(
                        code = response.code, message = response.message,
                        error = response.error
                    )
                }

                is ApiResponse.Exception -> {
                    response
                }

                is ApiResponse.Success -> {
                    ApiResponse.Success(
                        HeroDetailsModel(
                            id = response.result.result1.id,
                            name = response.result.result1.name,
                            image = response.result.result1.image,
                            comics = response.result.result2,
                            events = response.result.result3,
                            series = response.result.result4,
                        )
                    )
                }

                null -> {
                    ApiResponse.Exception(NoSuchElementException())
                }
            }
        }
    }

    data class Params(
        val id: String
    )
}
