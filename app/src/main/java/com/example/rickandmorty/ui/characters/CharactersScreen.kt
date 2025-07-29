package com.example.rickandmorty.ui.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entity.character.Character
import com.example.rickandmorty.domain.entity.character.CharacterStatus
import com.example.rickandmorty.ui.theme.Roboto

private const val STATUS_SEPARATOR = " - "

@Composable
fun CharactersScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    onSearchClick: () -> Unit,
    title: String,
    charactersToDisplay: List<Character>? = null
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        CharactersTopBar(
            value = uiState.searchQuery,
            onValueChange = { viewModel.filterSearch(it) },
            title = title
        )
    },
        bottomBar = {
            BottomBar(
                onFavoritesClick = onFavoritesClick,
                onSearchClick = onSearchClick
            )
        }) { paddingValues ->
        val charactersToShow = when {
            charactersToDisplay != null -> {
                if (uiState.searchQuery.isNotEmpty()) {
                    charactersToDisplay.filter {
                        it.name.lowercase().contains(uiState.searchQuery.lowercase())
                    }
                } else {
                    charactersToDisplay
                }
            }

            uiState.searchQuery.isNotEmpty() -> uiState.filteredCharacters
            else -> uiState.characters
        }

        if (charactersToShow != null) {
            LazyColumn(contentPadding = paddingValues) {
                items(
                    count = charactersToShow.size,
                    key = { index -> charactersToShow[index].id }) { index ->
                    CharacterItem(
                        character = charactersToShow[index],
                        onFavouriteClick = viewModel::toggleFavorite,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    onFavoritesClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(dimensionResource(R.dimen.height_bottom_bar))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(top = dimensionResource(R.dimen.padding_normal)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.size_icon))
                        .padding(bottom = dimensionResource(R.dimen.padding_mini))
                        .clickable { onSearchClick() })
                Text(
                    stringResource(R.string.action_search_characters),
                    color = colorResource(R.color.green_main),
                    fontSize = 12.sp,
                    fontFamily = Roboto
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(top = dimensionResource(R.dimen.padding_normal)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.ic_white_heart),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.size_icon))
                        .padding(bottom = dimensionResource(R.dimen.padding_mini))
                        .clickable { onFavoritesClick() })
                Text(
                    stringResource(R.string.action_select_favourites),
                    fontSize = 12.sp,
                    fontFamily = Roboto
                )
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    onFavouriteClick: (Int) -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(R.dimen.padding_normal),
                start = dimensionResource(R.dimen.padding_normal),
                end = dimensionResource(R.dimen.padding_normal)
            )
            .height(dimensionResource(R.dimen.height_character_card))
            .clickable { onCharacterClick(character.id) }, colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.radio_normal))
    ) {
        Row {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(dimensionResource(R.dimen.padding_small))
                    .size(dimensionResource(R.dimen.size_character_image))
                    .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.radio_normal)))
            )
            Column(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxHeight()
                    .padding(start = dimensionResource(R.dimen.padding_small)),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(R.color.blue_name),
                    fontFamily = Roboto,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = buildAnnotatedString {
                        val color = CharacterStatus.getStatusColor(character.status)
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(color)
                            )
                        ) {
                            append(character.status)
                        }
                        withStyle(
                            SpanStyle(
                                color = colorResource(R.color.blue_name)
                            )
                        ) {
                            append(STATUS_SEPARATOR)
                        }
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.blue_name)
                            )
                        ) {
                            append(character.originName)
                        }
                    },
                    style = TextStyle(
                        fontSize = 15.sp, fontFamily = Roboto
                    ),
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }
            Image(
                painter = if (character.isFavourite) painterResource(R.drawable.ic_heart)
                else painterResource(R.drawable.ic_empty_heart),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)
                    .clickable(onClick = { onFavouriteClick(character.id) })
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersTopBar(
    value: String, onValueChange: (String) -> Unit,
    title: String
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_top_title)
                )
                .statusBarsPadding()
                .padding(horizontal = dimensionResource(R.dimen.padding_normal))
        )
        OutlinedTextField(value = value, onValueChange = onValueChange, placeholder = {
            Text(
                stringResource(R.string.search_placeholder),
                fontSize = 15.sp,
                fontFamily = Roboto,
                color = colorResource(R.color.grey_search)
            )
        }, trailingIcon = {
            Row(
                modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (value.isNotEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.size_icon_search))
                            .clickable { onValueChange("") }
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_medium)))
                }

                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(dimensionResource(R.dimen.size_icon_search))
                )
            }
        },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = colorResource(R.color.green_main)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.padding_normal),
                    bottom = dimensionResource(R.dimen.padding_medium),
                    start = dimensionResource(R.dimen.padding_normal),
                    end = dimensionResource(R.dimen.padding_normal)
                )
                .height(dimensionResource(R.dimen.height_text_field_search))
        )
    }
}