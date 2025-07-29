package com.example.rickandmorty.domain.useCase.episodes

import com.example.rickandmorty.domain.entity.episode.Episode

interface EpisodeUseCase {
    suspend fun getEpisodesByIds(episodeIds: List<String>): List<Episode>
}