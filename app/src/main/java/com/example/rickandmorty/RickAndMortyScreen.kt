package com.example.rickandmorty

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.characters.CharacterViewModel
import com.example.rickandmorty.ui.characters.CharactersScreen
import com.example.rickandmorty.ui.episodes.CharacterDetailScreen

enum class RickAndMortyScreen {
    Characters,
    CharacterDetail,
    Favorites
}

const val CHARACTER_ID_PARAM = "characterId"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RickAndMortyApp(
    viewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold { _ ->
        NavHost(
            navController = navController,
            startDestination = RickAndMortyScreen.Characters.name,
        ) {
            composable(RickAndMortyScreen.Characters.name) {
                LaunchedEffect(Unit) {
                    viewModel.loadCharacters()
                }
                CharactersScreen(
                    onCharacterClick = { selectedCharacterId ->
                        navController.navigate(
                            "${RickAndMortyScreen.CharacterDetail.name}/$selectedCharacterId"
                        )
                    },
                    onFavoritesClick = { navController.navigate(RickAndMortyScreen.Favorites.name) },
                    title = stringResource(R.string.characters_title),
                    onSearchClick = {}
                )
            }

            composable("${RickAndMortyScreen.CharacterDetail.name}/{$CHARACTER_ID_PARAM}") { backStackEntry ->
                val characterId =
                    backStackEntry.arguments?.getString(CHARACTER_ID_PARAM)?.toIntOrNull() ?: 0
                CharacterDetailScreen(
                    characterId = characterId,
                    viewModel = viewModel,
                    onBackClick = {
                        navController.navigate(RickAndMortyScreen.Characters.name)
                    }
                )
            }

            composable(RickAndMortyScreen.Favorites.name) {
                viewModel.loadCharacters()
                val uiState by viewModel.uiState.collectAsState()
                val favoriteCharacters = uiState.characters?.filter { it.isFavourite }

                CharactersScreen(
                    viewModel = viewModel,
                    onCharacterClick = { selectedCharacterId ->
                        navController.navigate(
                            "${RickAndMortyScreen.CharacterDetail.name}/$selectedCharacterId"
                        )
                    },
                    onFavoritesClick = { },
                    title = stringResource(R.string.title_favorites),
                    charactersToDisplay = favoriteCharacters,
                    onSearchClick = { navController.navigate(RickAndMortyScreen.Characters.name) }
                )
            }
        }
    }
}