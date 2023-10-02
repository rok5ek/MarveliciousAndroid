package rokpetk.marvelicious.app.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import rokpetk.marvelicious.app.data.models.HeroesResponse

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(): ApiResponse<HeroesResponse>
}