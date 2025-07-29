package com.example.rickandmorty.domain.repository.episodes

import com.example.rickandmorty.domain.entity.episode.Episode

interface EpisodeRepository {
    suspend fun getEpisodesByIds(episodeIds: List<String>): List<Episode>
}