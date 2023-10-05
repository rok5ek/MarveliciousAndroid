package rokpetk.marvelicious.app.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rokpetk.marvelicious.app.data.models.ComicWrapperResponse
import rokpetk.marvelicious.app.data.models.EventWrapperResponse
import rokpetk.marvelicious.app.data.models.HeroWrapperResponse
import rokpetk.marvelicious.app.data.models.SeriesWrapperResponse

interface MarvelApiService {

    @GET("characters/{heroId}")
    suspend fun getHero(
        @Path("heroId") heroId: String
    ): Response<HeroWrapperResponse>

    @GET("characters")
    suspend fun getHeroes(
        @Query("nameStartsWith") nameStartsWith: String?,
        @Query("limit") limit: Int
    ): Response<HeroWrapperResponse>

    @GET("characters/{heroId}/comics")
    suspend fun getHeroComics(
        @Path("heroId") heroId: String
    ): Response<ComicWrapperResponse>

    @GET("characters/{heroId}/series")
    suspend fun getHeroSeries(
        @Path("heroId") heroId: String
    ): Response<SeriesWrapperResponse>

    @GET("characters/{heroId}/events")
    suspend fun getHeroEvents(
        @Path("heroId") heroId: String
    ): Response<EventWrapperResponse>
}