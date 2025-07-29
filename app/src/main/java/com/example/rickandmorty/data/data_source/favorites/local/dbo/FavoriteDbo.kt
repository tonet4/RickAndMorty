package com.example.rickandmorty.data.data_source.favorites.local.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITES_TABLE_NAME = "favorites"

@Entity(tableName = FAVORITES_TABLE_NAME)
data class FavoriteDbo(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val originName: String,
    val isFavourite: Boolean
)