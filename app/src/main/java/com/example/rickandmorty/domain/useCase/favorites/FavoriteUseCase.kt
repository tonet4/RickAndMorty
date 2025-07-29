package com.example.rickandmorty.domain.useCase.favorites

import com.example.rickandmorty.domain.entity.favorite.Favorite

interface FavoriteUseCase {
    suspend fun getFavorites(): List<Favorite>
    suspend fun addFavorite(favorite: Favorite)
    suspend fun removeFavorite(characterId: Int)
    suspend fun isFavorite(characterId: Int): Boolean
}