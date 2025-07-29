package com.example.rickandmorty.domain.useCase.favorites

import com.example.rickandmorty.domain.entity.favorite.Favorite
import com.example.rickandmorty.domain.repository.favorites.FavoriteRepository
import jakarta.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : FavoriteUseCase {

    override suspend fun getFavorites(): List<Favorite> =
        repository.getFavorites()

    override suspend fun addFavorite(favorite: Favorite) =
        repository.addFavorite(favorite)

    override suspend fun removeFavorite(characterId: Int) =
        repository.removeFavorite(characterId)

    override suspend fun isFavorite(characterId: Int): Boolean =
        repository.isFavorite(characterId)

}