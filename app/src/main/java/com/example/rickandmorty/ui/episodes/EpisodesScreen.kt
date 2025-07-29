package com.example.rickandmorty.ui.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entity.character.Character
import com.example.rickandmorty.domain.entity.character.CharacterStatus
import com.example.rickandmorty.domain.entity.episode.Episode
import com.example.rickandmorty.ui.characters.CharacterViewModel
import com.example.rickandmorty.ui.theme.Bangers
import com.example.rickandmorty.ui.theme.Roboto

private const val NOT_AVAILABLE = " -"

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(characterId) {
        val character = uiState.characters?.find { it.id == characterId }
        character?.let { viewModel.loadCharactersEpisodes(it) }
    }

    Scaffold(
        topBar = {
            DetailTopBar(onBackClick = onBackClick)
        }
    ) { paddingValues ->
        val character = uiState.characters?.find { it.id == characterId }
        CharacterDetail(
            character = character,
            episodes = uiState.selectedCharacterEpisodes,
            modifier = Modifier.padding(paddingValues),
            onFavouriteClick = viewModel::toggleFavorite,
        )
    }
}

@Composable
fun DetailTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = dimensionResource(R.dimen.padding_normal),
                bottom = dimensionResource(R.dimen.padding_large)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() }
                .padding(
                    end = dimensionResource(R.dimen.padding_normal),
                    start = dimensionResource(R.dimen.padding_normal)
                )
        )
        Text(
            stringResource(R.string.title_detail),
            fontSize = 18.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CharacterDetail(
    character: Character?,
    episodes: List<Episode>?,
    modifier: Modifier = Modifier,
    onFavouriteClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(R.dimen.padding_normal)
            )
    ) {
        character?.let {
            Box {
                AsyncImage(
                    model = it.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.height_detail_main_image))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.radio_normal)))
                )
                Image(
                    painter = if (character.isFavourite) painterResource(R.drawable.ic_detail_heart_favourite)
                    else painterResource(R.drawable.ic_detail_heart),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = dimensionResource(R.dimen.padding_small))
                        .align(Alignment.BottomEnd)
                        .offset(y = dimensionResource(R.dimen.offset_heart))
                        .clickable(onClick = { onFavouriteClick(character.id) })
                )
            }
            Text(
                text = character.name,
                fontFamily = Bangers,
                fontSize = 28.sp,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
            )
            Row(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_normal)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = CreateValueText(
                        character.species,
                        stringResource(R.string.label_specie),
                        valueStyle = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ),
                    modifier = Modifier.weight(1f),
                    fontSize = 15.sp,
                    fontFamily = Roboto
                )
                Text(
                    text = CreateValueText(
                        character.type, stringResource(R.string.label_type), valueStyle = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_normal)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = CreateValueText(
                            character.originName.substringBefore(" ("),
                            stringResource(R.string.label_from),
                            valueStyle = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline,
                                color = colorResource(R.color.yellow_main)
                            )
                        ),
                        fontSize = 15.sp,
                        fontFamily = Roboto
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_mini_small)))
                    Icon(
                        painter = painterResource(R.drawable.ic_map),
                        contentDescription = null,
                        tint = colorResource(R.color.yellow_main)
                    )
                }
                Text(
                    text = CreateValueText(
                        character.status,
                        stringResource(R.string.label_status),
                        valueStyle = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = colorResource(CharacterStatus.getStatusColor(character.status))
                        )
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = CreateValueText(
                    episodes?.firstOrNull()?.episode ?: stringResource(R.string.unknown),
                    stringResource(R.string.label_first_time_seen),
                    valueStyle = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_normal)),
                fontSize = 15.sp,
                fontFamily = Roboto
            )
            Text(
                text = CreateValueText(
                    episodes?.lastOrNull()?.episode ?: stringResource(R.string.unknown),
                    stringResource(R.string.label_last_time_seen),
                    valueStyle = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_normal)),
                fontSize = 15.sp,
                fontFamily = Roboto
            )
            Text(
                stringResource(R.string.list_of_episodes),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    ),
                fontSize = 15.sp,
                fontFamily = Roboto
            )
        }

        episodes?.let { episodeList ->
            LazyColumn {
                items(episodeList) { episode ->
                    EpisodeItem(episode = episode)
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: Episode) {
    Text(
        text = "${episode.episode} ${episode.name}",
        textDecoration = TextDecoration.Underline,
        color = colorResource(R.color.yellow_main),
        fontSize = 15.sp,
        fontFamily = Roboto,
        modifier = Modifier
            .padding(bottom = dimensionResource(R.dimen.padding_normal))
    )
}

private fun CreateValueText(
    value: String,
    label: String,
    valueStyle: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        append("$label ")
        if (value.isNotEmpty()) {
            withStyle(style = valueStyle) {
                append(value)
            }
        } else {
            append(NOT_AVAILABLE)
        }
    }
}