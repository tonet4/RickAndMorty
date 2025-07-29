package com.example.rickandmorty.data.data_source.episodes.remote.dto

data class EpisodeDto(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
)