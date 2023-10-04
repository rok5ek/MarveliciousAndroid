package rokpetk.marvelicious.app.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rokpetk.marvelicious.app.data.models.HeroesResponse

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(
        @Query("nameStartsWith") nameStartsWith: String?,
        @Query("limit") limit: Int
    ): ApiResponse<HeroesResponse>
}