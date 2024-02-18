package com.piriurna.pokecollect.android.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DanubeBlue = Color(0xFF5A8BCD)
val OldGold = Color(0xFFD5AC4A)
val MatisseBlue = Color(0xFF2062AC)
val TaraweraBlue = Color(0XFF083962)

val lightColors = lightColorScheme(
    primary = DanubeBlue,
    secondary = OldGold,
    tertiary = MatisseBlue,
    onPrimary = Color.White,
    surface = OldGold,
    onSurface = Color.White
)

val darkColors = darkColorScheme(
    primary = MatisseBlue,
    secondary = OldGold,
    tertiary = MatisseBlue,
    surface = OldGold,
    onSurface = Color.White,
    background = DanubeBlue,
    onPrimary = Color.White,
    onSecondary = Color.Black
)