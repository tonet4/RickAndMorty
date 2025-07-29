package com.example.rickandmorty.di

import com.example.rickandmorty.data.data_source.characters.CharacterDataSource
import com.example.rickandmorty.data.data_source.characters.remote.CharacterDataSourceImpl
import com.example.rickandmorty.data.data_source.characters.remote.api.CharacterApi
import com.example.rickandmorty.data.data_source.episodes.EpisodeDataSource
import com.example.rickandmorty.data.data_source.episodes.remote.EpisodeDataSourceImpl
import com.example.rickandmorty.data.data_source.episodes.remote.api.EpisodeApi
import com.example.rickandmorty.data.data_source.favorites.FavoriteDataSource
import com.example.rickandmorty.data.data_source.favorites.local.FavoriteDataSourceImpl
import com.example.rickandmorty.data.data_source.favorites.local.room.FavoriteDao
import com.example.rickandmorty.data.network_manager.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCharacterDataSource(
        api: CharacterApi,
        networkManager: NetworkManager
    ): CharacterDataSource = CharacterDataSourceImpl(api, networkManager)

    @Provides
    @Singleton
    fun provideEpisodeDataSource(
        api: EpisodeApi,
        networkManager: NetworkManager
    ): EpisodeDataSource = EpisodeDataSourceImpl(api, networkManager)

    @Provides
    @Singleton
    fun provideFavoriteDataSource(
        favoriteDao: FavoriteDao
    ): FavoriteDataSource = FavoriteDataSourceImpl(favoriteDao)
}