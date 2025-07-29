package com.example.rickandmorty.data.repository.episodes

import com.example.rickandmorty.data.data_source.episodes.EpisodeDataSource
import com.example.rickandmorty.data.data_source.episodes.remote.dto.EpisodeDto
import com.example.rickandmorty.data.network_manager.DataException
import com.example.rickandmorty.data.repository.ExceptionMapper.toDomain
import com.example.rickandmorty.domain.entity.episode.Episode
import com.example.rickandmorty.domain.repository.episodes.EpisodeRepository
import jakarta.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val dataSource: EpisodeDataSource
) : EpisodeRepository {

    override suspend fun getEpisodesByIds(episodeIds: List<String>): List<Episode> {
        return try {
            dataSource.getEpisodesByIds(episodeIds).map { it.toDomain() }
        } catch (e: DataException) {
            throw e.toDomain()
        }
    }

    private fun EpisodeDto.toDomain(): Episode = Episode(
        id = id,
        name = name,
        episode = episode,
        characters = extractCharacterIds()
    )

    private fun EpisodeDto.extractCharacterIds(): List<String> =
        characters.map { url ->
            url.substringAfterLast("/")
        }

}