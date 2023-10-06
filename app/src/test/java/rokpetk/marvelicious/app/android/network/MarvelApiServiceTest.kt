package rokpetk.marvelicious.app.android.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import rokpetk.marvelicious.app.data.Config
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.data.di.NetworkModule
import rokpetk.marvelicious.app.data.interceptors.AuthInterceptor
import java.net.HttpURLConnection


class MarvelServiceTest {

    private var mockWebServer = MockWebServer()
    private lateinit var apiService: MarvelApiService

    @Before
    fun setup() {
        mockWebServer.start()
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.baseUrl)
            .addConverterFactory(NetworkModule.provideJsonParser().asConverterFactory(contentType))
            .client(NetworkModule.provideHttpClient(AuthInterceptor()))
            .build()
        apiService = retrofit.create(MarvelApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testHeroResponse() {
        val responseJson =
            MarvelServiceTest::class.java.classLoader?.getResource("MarvelHeroResponse.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(responseJson.toString())
        mockWebServer.enqueue(response)

        runBlocking {
            val hero = apiService.getHero("1011334")
            assert(hero.body()?.data?.results?.first()?.name.equals("3-D Man"))
        }
    }
}