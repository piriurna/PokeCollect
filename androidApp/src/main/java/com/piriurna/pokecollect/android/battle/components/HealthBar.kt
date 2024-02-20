package com.piriurna.pokecollect.android.battle.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HealthBar(
    modifier: Modifier = Modifier,
    currentHp: Int,
    maxHp: Int,
    fillColor: Color = Color.Green
) {
    val animatedProgress =
        animateFloatAsState(targetValue = currentHp.toFloat()/ maxHp.toFloat(), label = "hpProgress")

    LinearProgressIndicator(
        modifier = modifier,
        progress = animatedProgress.value,
        color = fillColor
    )
}