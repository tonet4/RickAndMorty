package com.example.rickandmorty.data.data_source.characters

import com.example.rickandmorty.data.data_source.characters.remote.dto.CharacterDto

interface CharacterDataSource {
    suspend fun getCharacters(): List<CharacterDto>
}