package com.example.rickandmorty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.rickandmorty.R

val Roboto = FontFamily(
    Font(R.font.roboto),
    Font(R.font.roboto_italic)
)

val Bangers = FontFamily(
    Font(R.font.bangers_regular)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Bangers,
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
    ),
)
