package rokpetk.marvelicious.app.domain.source.remote

interface AppRepository {
    suspend fun getHeroes()
}