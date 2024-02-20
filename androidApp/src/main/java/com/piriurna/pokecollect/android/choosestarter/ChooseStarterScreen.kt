package com.piriurna.pokecollect.android.choosestarter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.piriurna.pokecollect.domain.destinations.Destination

@Composable
fun ChooseStarterScreen(
    modifier: Modifier = Modifier,
    viewModel: ChooseStarterViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        uiState.starterOptions.forEach {
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.choosePokemon(it)
                        navController.navigate(Destination.WildEncounterScreen.route)
                    },
                model = it.imageUrl,
                contentDescription = ""
            )
        }
    }
}