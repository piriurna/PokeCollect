package com.piriurna.pokecollect.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.pokecollect.android.bottomnavigation.BottomNavigator
import com.piriurna.pokecollect.android.bottomnavigation.items.BottomNavigationItem
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
    val items = listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            text = "Home",
            route = "display"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Info,
            text = "Pokedex",
            route = "pokedex"
        )
    )
    Scaffold(
        bottomBar = {
            BottomNavigator(items = items, navController = navHostController)
        }
    ) { paddingValues ->
        NavHost(
            startDestination = "pokedex",
            navController = navHostController
        ) {
            composable("display") {
                PokemonDisplayScreen(
                    modifier = Modifier.padding(paddingValues),
                    viewModel = pokemonDisplayViewModel
                )
            }

            composable("pokedex") {
                PokedexScreen(
                    modifier = Modifier.padding(paddingValues),
                    viewModel = koinViewModel<PokedexViewModel>()
                )
            }
        }
    }
}