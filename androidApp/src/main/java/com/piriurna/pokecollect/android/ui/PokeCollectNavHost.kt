package com.piriurna.pokecollect.android.ui

import androidx.compose.animation.AnimatedVisibility
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
import com.piriurna.pokecollect.android.choosestarter.navigation.chooseStarterNavigation
import com.piriurna.pokecollect.android.pokedex.navigation.pokedexNavigation
import com.piriurna.pokecollect.android.pokemondisplay.navigation.pokemonDisplayNavigation
import com.piriurna.pokecollect.android.splash.navigation.splashNavigation
import com.piriurna.pokecollect.domain.destinations.Destination

@Composable
fun PokeCollectNavHost(
    navHostController: NavHostController
) {
    val rootScreens = listOf(
        BottomNavigationItem(
            icon = R.drawable.ic_wild_grass,
            text = stringResource(R.string.pokemon_encounter),
            route = Destination.WildEncounterScreen.route
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_pokeball,
            text = stringResource(R.string.pokedex),
            route = Destination.PokedexScreen.route
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
            startDestination = Destination.SplashScreen.route,
            navController = navHostController
        ) {

            splashNavigation(paddingValues, navHostController)

            chooseStarterNavigation(paddingValues, navHostController)

            pokemonDisplayNavigation(paddingValues, navHostController)

            pokedexNavigation(paddingValues)

            battleNavigation(paddingValues, navHostController)
        }
    }
}