package com.example.rickandmorty.domain.useCase.characters

import com.example.rickandmorty.domain.entity.character.Character
import com.example.rickandmorty.domain.repository.characters.CharacterRepository
import jakarta.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository
) : CharacterUseCase {

    override suspend fun getCharacters(): List<Character> = repository.getCharacters()

}