package rokpetk.marvelicious.app.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rokpetk.marvelicious.app.data.repositories.AppRepositoryImpl
import rokpetk.marvelicious.app.domain.repositories.AppRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppRepository(appRepository: AppRepositoryImpl): AppRepository {
        return appRepository
    }
}
