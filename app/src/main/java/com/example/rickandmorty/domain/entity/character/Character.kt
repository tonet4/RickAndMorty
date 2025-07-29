package com.example.rickandmorty.domain.entity.character

import com.example.rickandmorty.R

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val originName: String,
    val image: String,
    val episodes: List<String>,
    val isFavourite: Boolean,
    val species: String,
    val type: String
)

enum class CharacterStatus(val displayName: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    companion object {
        fun fromString(status: String): CharacterStatus =
            values().find { it.displayName == status } ?: UNKNOWN

        fun getStatusColor(status: String): Int {
            return when (fromString(status)) {
                ALIVE -> R.color.green_main
                DEAD -> R.color.red
                UNKNOWN -> R.color.blue_name
            }
        }
    }
}