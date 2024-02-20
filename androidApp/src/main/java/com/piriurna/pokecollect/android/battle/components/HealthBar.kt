package com.piriurna.pokecollect.android.battle.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HealthBar(
    modifier: Modifier = Modifier,
    currentHp: Int,
    maxHp: Int,
    fillColor: Color = MaterialTheme.colorScheme.primary
) {
    val animatedProgress =
        animateFloatAsState(targetValue = currentHp.toFloat()/ maxHp.toFloat(), label = "hpProgress")

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = animatedProgress.value,
            color = fillColor
        )
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = "$currentHp/$maxHp",
            textAlign = TextAlign.Center
        )
    }
}