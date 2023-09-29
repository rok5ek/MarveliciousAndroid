package rokpetk.marvelicious.app.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import rokpetk.marvelicious.app.data.BuildConfig
import rokpetk.marvelicious.app.data.Config
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val timestamp = Config.timestamp()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(Config.timestampQueryParam, timestamp)
            .addQueryParameter(Config.apiKeyQueryParam, BuildConfig.MARVEL_API_PUBLIC_KEY)
            .addQueryParameter(
                Config.hashQueryParam, Config.hash(
                    timestamp = timestamp,
                    privateKey = BuildConfig.MARVEL_API_PRIVATE_KEY,
                    publicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
                )
            )
            .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}