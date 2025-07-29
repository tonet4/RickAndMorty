package com.example.rickandmorty.data.data_source.favorites.local

import com.example.rickandmorty.data.data_source.favorites.FavoriteDataSource
import com.example.rickandmorty.data.data_source.favorites.local.dbo.FavoriteDbo
import com.example.rickandmorty.data.data_source.favorites.local.room.FavoriteDao
import jakarta.inject.Inject

class FavoriteDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteDataSource {

    override suspend fun getFavorites(): List<FavoriteDbo> = favoriteDao.getAll()

    override suspend fun addFavorite(favorite: FavoriteDbo) {
        favoriteDao.insert(favorite)
    }

    override suspend fun removeFavorite(characterId: Int) {
        favoriteDao.delete(characterId)
    }

    override suspend fun isFavorite(characterId: Int): Boolean =
        favoriteDao.isFavorite(characterId)

}