package com.piriurna.pokecollect.android.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.piriurna.pokecollect.android.R
import com.piriurna.pokecollect.android.battle.navigation.battleNavigation
import com.piriurna.pokecollect.android.bottomnavigation.BottomNavigator
import com.piriurna.pokecollect.android.bottomnavigation.items.BottomNavigationItem
import com.piriurna.pokecollect.android.pokedex.navigation.PokedexDestinations
import com.piriurna.pokecollect.android.pokedex.navigation.pokedexNavigation
import com.piriurna.pokecollect.android.pokemondisplay.navigation.PokemonDisplayDestinations.PokemonDisplayRoute
import com.piriurna.pokecollect.android.pokemondisplay.navigation.pokemonDisplayNavigation

@Composable
fun PokeCollectNavHost(
    navHostController: NavHostController
) {
    val rootScreens = listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            text = stringResource(R.string.pokemon_encounter),
            route = PokemonDisplayRoute
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Info,
            text = stringResource(R.string.pokedex),
            route = PokedexDestinations.PokedexRoute
        )
    )
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = rootScreens.any { it.route == currentRoute }) {
                BottomNavigator(items = rootScreens, navController = navHostController)
            }
        }
    ) { paddingValues ->
        NavHost(
            startDestination = PokemonDisplayRoute,
            navController = navHostController
        ) {
            pokemonDisplayNavigation(paddingValues, navHostController)

            pokedexNavigation(paddingValues)

            battleNavigation(paddingValues, navHostController)
        }
    }
}