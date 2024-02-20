package com.piriurna.pokecollect.android.battle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.piriurna.pokecollect.android.battle.components.HealthBar
import com.piriurna.pokecollect.android.pokedex.navigation.PokedexDestinations

@Composable
fun BattleScreen(
    modifier: Modifier = Modifier,
    viewModel: BattleViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value

    LaunchedEffect(Unit) {
        viewModel.setup()
    }

    LaunchedEffect(uiState.battleEnded) {
        if (uiState.playerPokemon != null && uiState.enemyPokemon != null) {
            viewModel.battleFinished()
            navController.navigate(PokedexDestinations.PokedexRoute)
        }
    }

    if(uiState.enemyPokemon !=null && uiState.playerPokemon != null) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HealthBar(
                    modifier = Modifier
                        .width(100.dp)
                        .height(36.dp),
                    currentHp = uiState.enemyPokemon.currentHp,
                    maxHp = uiState.enemyPokemon.totalHp,
                    fillColor = Color.Red
                )

                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = uiState.enemyPokemon.imageUrl,
                    contentDescription = ""
                )
            }

            Button(onClick = { viewModel.battlePokemons() }) {
                Text(text = "Battle")
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HealthBar(
                    modifier = Modifier
                        .width(100.dp)
                        .height(36.dp),
                    currentHp = uiState.playerPokemon.currentHp,
                    maxHp = uiState.playerPokemon.totalHp
                )
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = uiState.playerPokemon.imageUrl,
                    contentDescription = ""
                )
            }
        }
    }
}