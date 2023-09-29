package rokpetk.marvelicious.app.domain.repositories

interface AppRepository {
    suspend fun getHeroes()
}