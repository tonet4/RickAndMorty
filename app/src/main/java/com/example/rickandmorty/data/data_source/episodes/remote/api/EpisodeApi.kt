package com.example.rickandmorty.data.data_source.episodes.remote.api

import com.example.rickandmorty.data.data_source.episodes.remote.dto.EpisodeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") episodeIds: String): Response<List<EpisodeDto>>
}