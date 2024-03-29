package com.piriurna.pokecollect.android.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piriurna.pokecollect.android.pokedex.ui.components.PokedexItem
import com.piriurna.pokecollect.android.ui.theme.spacing


@Composable
fun PokedexScreen(
    modifier: Modifier = Modifier,
    viewModel: PokedexViewModel
) {
    val uiState = viewModel.uiState.value

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(MaterialTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        items(uiState.pokemonList) {
            PokedexItem(pokemon = it, backgroundColor = MaterialTheme.colorScheme.primary)
        }
    }
}