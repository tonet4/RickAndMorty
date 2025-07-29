package com.example.rickandmorty.data.data_source.characters.remote

import com.example.rickandmorty.data.data_source.characters.CharacterDataSource
import com.example.rickandmorty.data.data_source.characters.remote.api.CharacterApi
import com.example.rickandmorty.data.data_source.characters.remote.dto.CharacterDto
import com.example.rickandmorty.data.network_manager.NetworkManager
import jakarta.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val characterApi: CharacterApi,
    private val networkManager: NetworkManager
) : CharacterDataSource {

    override suspend fun getCharacters(): List<CharacterDto> =
        networkManager.getResponse { characterApi.getCharacters() }.results

}