package com.piriurna.pokecollect.android.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piriurna.pokecollect.android.pokedex.ui.components.PokedexItem


@Composable
fun PokedexScreen(
    modifier: Modifier = Modifier,
    viewModel: PokedexViewModel
) {
    val uiState = viewModel.uiState.value
    LaunchedEffect(Unit) {
        viewModel.setupScreen()
    }


    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.pokemonList) {
            PokedexItem(pokemon = it, backgroundColor = MaterialTheme.colorScheme.primary)
        }
    }
}