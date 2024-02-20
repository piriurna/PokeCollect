package com.piriurna.pokecollect.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val small: Dp = Dp.Unspecified,
    val medium: Dp = Dp.Unspecified,
    val large: Dp = Dp.Unspecified,
    val extraLarge: Dp = Dp.Unspecified
)


val LocalSpacing = staticCompositionLocalOf {
    Spacing(
        small = 4.dp,
        medium = 8.dp,
        large = 16.dp,
        extraLarge = 32.dp
    )
}


val MaterialTheme.spacing: Spacing
    @Composable
    get() = LocalSpacing.current