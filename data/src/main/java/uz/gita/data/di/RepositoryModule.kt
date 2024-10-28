package uz.gita.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.data.repository.AppRepository
import uz.gita.data.repository.AuthRepository
import uz.gita.data.repository.CardRepository
import uz.gita.data.repository.HomeRepository
import uz.gita.data.repository.impl.AppRepositoryImpl
import uz.gita.data.repository.impl.AuthRepositoryImpl
import uz.gita.data.repository.impl.CardRepositoryImpl
import uz.gita.data.repository.impl.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun provideAuthRepositoryImpl(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun provideAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository
}