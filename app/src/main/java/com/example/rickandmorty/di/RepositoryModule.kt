package com.example.rickandmorty.di

import com.example.rickandmorty.data.data_source.characters.CharacterDataSource
import com.example.rickandmorty.data.data_source.episodes.EpisodeDataSource
import com.example.rickandmorty.data.data_source.favorites.FavoriteDataSource
import com.example.rickandmorty.data.repository.characters.CharacterRepositoryImpl
import com.example.rickandmorty.data.repository.episodes.EpisodeRepositoryImpl
import com.example.rickandmorty.data.repository.favorites.FavoriteRepositoryImpl
import com.example.rickandmorty.domain.repository.characters.CharacterRepository
import com.example.rickandmorty.domain.repository.episodes.EpisodeRepository
import com.example.rickandmorty.domain.repository.favorites.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        dataSource: CharacterDataSource
    ): CharacterRepository = CharacterRepositoryImpl(dataSource)

    @Provides
    fun provideEpisodeRepository(
        dataSource: EpisodeDataSource
    ): EpisodeRepository = EpisodeRepositoryImpl(dataSource)

    @Provides
    fun provideFavoriteRepository(
        dataSource: FavoriteDataSource
    ): FavoriteRepository = FavoriteRepositoryImpl(dataSource)

}