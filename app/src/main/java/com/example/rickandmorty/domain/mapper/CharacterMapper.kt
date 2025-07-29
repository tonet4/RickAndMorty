package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.entity.favorite.Favorite
import com.example.rickandmorty.domain.entity.character.Character

fun Character.toFavorite() = Favorite(
    id = id,
    name = name,
    image = image,
    status = status,
    originName = originName,
    isFavourite = isFavourite
)