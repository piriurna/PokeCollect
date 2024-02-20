package com.piriurna.pokecollect.android.battle.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.piriurna.pokecollect.android.battle.BattleScreen
import com.piriurna.pokecollect.android.battle.navigation.BattleDestinations.AttackingPokemonIdArg
import com.piriurna.pokecollect.android.battle.navigation.BattleDestinations.BattleHomePageFullRoute
import com.piriurna.pokecollect.android.battle.navigation.BattleDestinations.DefendingPokemonIdArg
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.battleNavigation(paddingValues: PaddingValues, navController: NavController) {
    composable(
        route = BattleHomePageFullRoute,
        arguments = listOf(
            navArgument(AttackingPokemonIdArg) { type = NavType.IntType },
            navArgument(DefendingPokemonIdArg) { type = NavType.IntType }
        )
    ) {
        BattleScreen(
            modifier = Modifier.padding(paddingValues),
            viewModel = koinViewModel(),
            navController = navController
        )
    }
}

