package com.example.rickandmorty.data.data_source.favorites

import com.example.rickandmorty.data.data_source.favorites.local.dbo.FavoriteDbo

interface FavoriteDataSource {
    suspend fun getFavorites(): List<FavoriteDbo>
    suspend fun addFavorite(favorite: FavoriteDbo)
    suspend fun removeFavorite(characterId: Int)
    suspend fun isFavorite(characterId: Int): Boolean
}