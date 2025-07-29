package com.example.rickandmorty.domain.entity.episode

data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
)