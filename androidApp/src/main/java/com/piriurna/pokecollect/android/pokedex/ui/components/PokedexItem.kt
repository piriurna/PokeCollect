package com.piriurna.pokecollect.android.pokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.piriurna.pokecollect.domain.models.Pokemon

@Composable
fun PokedexItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    imageSize: Dp = 100.dp
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.size(imageSize),
                model = pokemon.imageUrl,
                contentDescription = "",
                colorFilter = if(!pokemon.owned) ColorFilter.tint(Color.Black) else null
            )
        }
    }
}