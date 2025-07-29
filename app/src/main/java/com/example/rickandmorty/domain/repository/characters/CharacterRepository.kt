package com.example.rickandmorty.domain.repository.characters

import com.example.rickandmorty.domain.entity.character.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}