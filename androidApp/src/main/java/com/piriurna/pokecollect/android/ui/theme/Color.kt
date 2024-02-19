package com.piriurna.pokecollect.android.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val MidnightBlue = Color(0xFF003049)
val Orange = Color(0xFFF77F00)
val PoppyRed = Color(0xFFD62828)

val JetBlack = Color(0xFF323031)
val White = Color(0xFFFBFBFB)

val lightColors = lightColorScheme(
    primary = MidnightBlue,
    secondary = Orange,
    tertiary = PoppyRed,
    onPrimary = White,
    background = White,
    onSecondary = JetBlack
)

val darkColors = darkColorScheme(
    primary = MidnightBlue,
    secondary = Orange,
    tertiary = PoppyRed,
    onPrimary = White,
    onSecondary = JetBlack
)