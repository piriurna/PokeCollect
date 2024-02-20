package com.piriurna.pokecollect.android.pokemondisplay.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.piriurna.pokecollect.android.pokemondisplay.ui.theme.DisplayScreenDimensions.DisplayScreenPokemonImageSize
import com.piriurna.pokecollect.android.ui.theme.spacing
import com.piriurna.pokecollect.domain.models.Pokemon

@Composable
fun BattlePoxedexItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier.size(DisplayScreenPokemonImageSize),
            model = pokemon.imageUrl,
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Text(text = pokemon.name, color = Color.White)
    }
}