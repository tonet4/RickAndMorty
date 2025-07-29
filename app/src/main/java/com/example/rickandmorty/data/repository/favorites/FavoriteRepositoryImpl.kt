package com.example.rickandmorty.data.repository.favorites

import com.example.rickandmorty.data.data_source.favorites.FavoriteDataSource
import com.example.rickandmorty.data.data_source.favorites.local.dbo.FavoriteDbo
import com.example.rickandmorty.domain.entity.favorite.Favorite
import com.example.rickandmorty.domain.repository.favorites.FavoriteRepository
import jakarta.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {

    override suspend fun getFavorites(): List<Favorite> =
        favoriteDataSource.getFavorites().map { it.toDomain() }

    override suspend fun addFavorite(favorite: Favorite) {
        favoriteDataSource.addFavorite(favorite.toDbo())
    }

    override suspend fun removeFavorite(characterId: Int) {
        favoriteDataSource.removeFavorite(characterId)
    }

    override suspend fun isFavorite(characterId: Int): Boolean =
        favoriteDataSource.isFavorite(characterId)

    private fun FavoriteDbo.toDomain() = Favorite(
        id = id,
        name = name,
        image = image,
        status = status,
        originName = originName,
        isFavourite = isFavourite
    )

    fun Favorite.toDbo() = FavoriteDbo(
        id = id,
        name = name,
        image = image,
        status = status,
        originName = originName,
        isFavourite = isFavourite
    )
}