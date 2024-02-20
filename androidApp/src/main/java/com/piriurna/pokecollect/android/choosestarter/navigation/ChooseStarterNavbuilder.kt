package com.piriurna.pokecollect.android.choosestarter.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.piriurna.pokecollect.android.choosestarter.ChooseStarterScreen
import com.piriurna.pokecollect.domain.destinations.Destination
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.chooseStarterNavigation(paddingValues: PaddingValues, navController: NavController) {
    composable(Destination.StarterSelection.route) {
        ChooseStarterScreen(
            modifier = Modifier.padding(paddingValues),
            viewModel = koinViewModel(),
            navController = navController
        )
    }
}