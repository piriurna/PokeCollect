package com.piriurna.pokecollect.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.pokecollect.android.pokedex.PokedexScreen
import com.piriurna.pokecollect.android.pokedex.PokedexViewModel
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayScreen
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokeCollectNavHost(
    navHostController: NavHostController,
    pokemonDisplayViewModel: PokemonDisplayViewModel
) {
    NavHost(
        startDestination = "pokedex",
        navController = navHostController
    ) {
        composable("display") {
            PokemonDisplayScreen(viewModel = pokemonDisplayViewModel)
        }

        composable("pokedex") {
            PokedexScreen(viewModel = koinViewModel<PokedexViewModel>())
        }
    }
}