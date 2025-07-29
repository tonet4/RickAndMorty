package com.example.rickandmorty.data.data_source.favorites.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rickandmorty.data.data_source.favorites.local.dbo.FavoriteDbo

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: FavoriteDbo)

    @Query("DELETE FROM favorites WHERE id = :characterId")
    suspend fun delete(characterId: Int)

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteDbo>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :characterId)")
    suspend fun isFavorite(characterId: Int): Boolean
}