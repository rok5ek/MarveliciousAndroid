package rokpetk.marvelicious.app.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import rokpetk.marvelicious.app.data.repositories.AppRepositoryImpl
import rokpetk.marvelicious.app.domain.repositories.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideAppRepository(appRepository: AppRepositoryImpl): AppRepository {
        return appRepository
    }
}
