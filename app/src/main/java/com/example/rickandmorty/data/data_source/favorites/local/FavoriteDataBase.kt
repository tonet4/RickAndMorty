package com.example.rickandmorty.data.data_source.favorites.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.data_source.favorites.local.dbo.FavoriteDbo
import com.example.rickandmorty.data.data_source.favorites.local.room.FavoriteDao

@Database(
    entities = [FavoriteDbo::class],
    version = 1,
    exportSchema = false
)

abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}