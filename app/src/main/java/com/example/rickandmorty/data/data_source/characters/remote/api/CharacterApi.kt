package com.example.rickandmorty.data.data_source.characters.remote.api

import com.example.rickandmorty.data.data_source.characters.remote.dto.CharacterPageDto
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterPageDto>
}