package tj.mangotask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import tj.mangotask.data.local.LocalDataSource
import tj.mangotask.data.remote.AuthApi
import tj.mangotask.data.remote.AuthRemoteDataSource
import tj.mangotask.data.remote.UserApi
import tj.mangotask.data.remote.UserRemoteDataSource
import tj.mangotask.data.repository.AuthRepositoryImpl
import tj.mangotask.data.repository.UserRepositoryImpl
import tj.mangotask.domain.repository.AuthRepository
import tj.mangotask.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        api: AuthApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ) = AuthRemoteDataSource(api, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        api: UserApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ) = UserRemoteDataSource(api, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        @ApplicationContext appContext: Context,
    ) = LocalDataSource(appContext)

    @Singleton
    @Provides
    fun provideAuthRepository(
        localDataSource: LocalDataSource,
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthRepository = AuthRepositoryImpl(localDataSource, authRemoteDataSource)

    @Singleton
    @Provides
    fun provideUserRepository(
        localDataSource: LocalDataSource,
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository = UserRepositoryImpl(localDataSource, userRemoteDataSource)

}