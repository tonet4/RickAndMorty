package com.example.rickandmorty.data.data_source.characters.remote.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val origin: OriginDto,
    val image: String,
    val episode: List<String>,
    val species: String,
    val type: String
)