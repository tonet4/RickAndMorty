package com.example.rickandmorty.domain.useCase.characters

import com.example.rickandmorty.domain.entity.character.Character

interface CharacterUseCase {
    suspend fun getCharacters(): List<Character>
}