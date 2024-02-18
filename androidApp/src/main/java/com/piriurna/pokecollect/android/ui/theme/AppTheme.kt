package com.piriurna.pokecollect.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkMode)
        darkColors
    else
        lightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}