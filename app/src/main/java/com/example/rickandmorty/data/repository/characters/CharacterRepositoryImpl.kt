package com.example.rickandmorty.data.repository.characters

import com.example.rickandmorty.data.data_source.characters.CharacterDataSource
import com.example.rickandmorty.data.data_source.characters.remote.dto.CharacterDto
import com.example.rickandmorty.data.network_manager.DataException
import com.example.rickandmorty.data.repository.ExceptionMapper.toDomain
import com.example.rickandmorty.domain.entity.character.Character
import com.example.rickandmorty.domain.repository.characters.CharacterRepository
import javax.inject.Inject
import kotlin.String

class CharacterRepositoryImpl @Inject constructor(
    private val dataSource: CharacterDataSource
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return try {
            dataSource.getCharacters().map { it.toDomain() }
        } catch (e: DataException) {
            throw e.toDomain()
        }
    }

    private fun CharacterDto.toDomain(): Character = Character(
        id = id,
        name = name,
        status = status,
        originName = origin.name,
        image = image,
        episodes = extractEpisodeIds(),
        isFavourite = false,
        species = species,
        type = type
    )

    private fun CharacterDto.extractEpisodeIds(): List<String> =
        episode.map { url ->
            url.substringAfterLast("/")
        }

}