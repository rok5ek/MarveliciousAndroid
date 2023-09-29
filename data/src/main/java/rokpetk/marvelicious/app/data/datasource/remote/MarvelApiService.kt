package rokpetk.marvelicious.app.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import rokpetk.marvelicious.app.data.models.HeroesResponse

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(): Response<HeroesResponse>
}