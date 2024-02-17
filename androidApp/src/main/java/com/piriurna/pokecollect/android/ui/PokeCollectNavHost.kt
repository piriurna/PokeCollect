package com.piriurna.pokecollect.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayScreen
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayViewModel

@Composable
fun PokeCollectNavHost(
    navHostController: NavHostController,
    pokemonDisplayViewModel: PokemonDisplayViewModel
) {
    NavHost(
        startDestination = "display",
        navController = navHostController
    ) {
        composable("display") {
            PokemonDisplayScreen(viewModel = pokemonDisplayViewModel)
        }
    }
}