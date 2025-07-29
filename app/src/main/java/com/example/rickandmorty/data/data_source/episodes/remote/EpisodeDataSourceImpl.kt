package com.example.rickandmorty.data.data_source.episodes.remote

import com.example.rickandmorty.data.data_source.episodes.EpisodeDataSource
import com.example.rickandmorty.data.data_source.episodes.remote.api.EpisodeApi
import com.example.rickandmorty.data.data_source.episodes.remote.dto.EpisodeDto
import com.example.rickandmorty.data.network_manager.NetworkManager
import jakarta.inject.Inject

class EpisodeDataSourceImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val networkManager: NetworkManager
) : EpisodeDataSource {
    override suspend fun getEpisodesByIds(episodeIds: List<String>): List<EpisodeDto> {
        val episodesParam = episodeIds.joinToString(",")
        return networkManager.getResponse {
            episodeApi.getEpisodesByIds(episodesParam)
        }
    }
}