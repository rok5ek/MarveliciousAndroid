package rokpetk.marvelicious.app.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import rokpetk.marvelicious.app.data.Config
import rokpetk.marvelicious.app.data.datasource.remote.MarvelApiService
import rokpetk.marvelicious.app.data.interceptors.AuthInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(authInterceptor)
        return httpClient.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): MarvelApiService {
        val json = Json {
            // json will be pretty printed
            prettyPrint = true
            // accept malformed input
            isLenient = true
            // unknown properties should be ignored instead of throwing SerializationException
            ignoreUnknownKeys = true
            // should nulls be encoded and be present in JSON object when decoding
            explicitNulls = false
        }
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
        return retrofit.create(MarvelApiService::class.java)
    }
}
