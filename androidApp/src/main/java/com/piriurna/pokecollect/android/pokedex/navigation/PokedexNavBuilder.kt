package com.piriurna.pokecollect.android.pokedex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.pokecollect.android.pokedex.PokedexScreen
import com.piriurna.pokecollect.android.pokedex.PokedexViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.pokedexNavigation(paddingValues: PaddingValues) {
    composable("pokedex") {
        PokedexScreen(
            modifier = androidx.compose.ui.Modifier.padding(paddingValues),
            viewModel = koinViewModel<PokedexViewModel>()
        )
    }
}