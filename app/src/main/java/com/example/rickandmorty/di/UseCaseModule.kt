package com.example.rickandmorty.di

import com.example.rickandmorty.domain.repository.characters.CharacterRepository
import com.example.rickandmorty.domain.repository.episodes.EpisodeRepository
import com.example.rickandmorty.domain.repository.favorites.FavoriteRepository
import com.example.rickandmorty.domain.useCase.characters.CharacterUseCase
import com.example.rickandmorty.domain.useCase.characters.CharacterUseCaseImpl
import com.example.rickandmorty.domain.useCase.episodes.EpisodeUseCase
import com.example.rickandmorty.domain.useCase.episodes.EpisodeUseCaseImpl
import com.example.rickandmorty.domain.useCase.favorites.FavoriteUseCase
import com.example.rickandmorty.domain.useCase.favorites.FavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCharacterUseCase(repository: CharacterRepository): CharacterUseCase =
        CharacterUseCaseImpl(repository)

    @Provides
    fun provideEpisodeUseCase(repository: EpisodeRepository): EpisodeUseCase =
        EpisodeUseCaseImpl(repository)

    @Provides
    fun provideFavoriteUseCase(repository: FavoriteRepository): FavoriteUseCase =
        FavoriteUseCaseImpl(repository)

}