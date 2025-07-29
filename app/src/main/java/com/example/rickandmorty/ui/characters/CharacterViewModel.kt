package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.entity.character.Character
import com.example.rickandmorty.domain.entity.episode.Episode
import com.example.rickandmorty.domain.exception.DomainError
import com.example.rickandmorty.domain.exception.DomainException
import com.example.rickandmorty.domain.mapper.toFavorite
import com.example.rickandmorty.domain.repository.favorites.FavoriteRepository
import com.example.rickandmorty.domain.useCase.characters.CharacterUseCase
import com.example.rickandmorty.domain.useCase.episodes.EpisodeUseCase
import com.example.rickandmorty.domain.useCase.favorites.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterUiState(
    val characters: List<Character>? = null,
    val selectedCharacterEpisodes: List<Episode>? = null,
    val error: DomainError = DomainError.NoError,
    val filteredCharacters: List<Character>? = null,
    val searchQuery: String = ""
)

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val episodeUseCase: EpisodeUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch() {
            try {
                val characters = characterUseCase.getCharacters()
                val charactersWithFavorites = characters.map { character ->
                    character.copy(isFavourite = favoriteUseCase.isFavorite(character.id))
                }
                _uiState.update { it.copy(characters = charactersWithFavorites) }

            } catch (e: DomainException) {
                _uiState.update { it.copy(error = e.error) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = DomainError.Unknown) }
            }
        }
    }

    fun loadCharactersEpisodes(character: Character) {
        viewModelScope.launch() {
            try {
                _uiState.update {
                    it.copy(
                        selectedCharacterEpisodes = episodeUseCase.getEpisodesByIds(
                            character.episodes
                        )
                    )
                }
            } catch (e: DomainException) {
                _uiState.update { it.copy(error = e.error) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = DomainError.Unknown) }
            }
        }
    }

    fun toggleFavorite(characterId: Int) {
        viewModelScope.launch() {
            try {
                val currentCharacters = uiState.value.characters
                val character = uiState.value.characters?.find { it.id == characterId }
                character?.let {
                    if (it.isFavourite) favoriteUseCase.removeFavorite(characterId)
                    else favoriteUseCase.addFavorite(it.toFavorite())

                    val updatedCharacter = it.copy(isFavourite = !it.isFavourite)
                    val updatedList = currentCharacters?.map { char ->
                        if (char.id == characterId) updatedCharacter else char
                    }
                    _uiState.value = uiState.value.copy(characters = updatedList)
                }
            } catch (e: DomainException) {
                _uiState.update { it.copy(error = e.error) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = DomainError.Unknown) }
            }
        }
    }

    fun filterSearch(characterName: String) {
        viewModelScope.launch() {
            try {
                loadCharacters()
                val searchQuery = characterName.trim()
                val currentCharacters = uiState.value.characters
                val filteredList = currentCharacters?.filter { character ->
                    character.name.lowercase().contains(searchQuery.lowercase())
                }
                if (searchQuery.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            filteredCharacters = filteredList,
                            searchQuery = characterName
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            filteredCharacters = null,
                            searchQuery = ""
                        )
                    }
                }
            } catch (e: DomainException) {
                _uiState.update { it.copy(error = e.error) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = DomainError.Unknown) }
            }
        }
    }
}