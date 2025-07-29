package com.example.rickandmorty.data.data_source.episodes

import com.example.rickandmorty.data.data_source.episodes.remote.dto.EpisodeDto

interface EpisodeDataSource {
    suspend fun getEpisodesByIds(episodeIds: List<String>): List<EpisodeDto>
}