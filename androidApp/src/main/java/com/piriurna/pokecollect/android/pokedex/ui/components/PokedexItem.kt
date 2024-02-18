package com.piriurna.pokecollect.android.pokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.piriurna.pokecollect.domain.models.Pokemon

@Composable
fun PokedexItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(model = pokemon.imageUrl, contentDescription = "")

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = pokemon.name, color = Color.White)
        }
    }
}