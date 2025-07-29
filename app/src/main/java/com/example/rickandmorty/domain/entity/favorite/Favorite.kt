package com.example.rickandmorty.domain.entity.favorite

data class Favorite(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val originName: String,
    val isFavourite: Boolean
)