package com.example.rickandmorty.domain.useCase.episodes

import com.example.rickandmorty.domain.entity.episode.Episode
import com.example.rickandmorty.domain.repository.episodes.EpisodeRepository
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val repository: EpisodeRepository
) : EpisodeUseCase {
    override suspend fun getEpisodesByIds(episodeIds: List<String>): List<Episode> =
        repository.getEpisodesByIds(episodeIds)

}