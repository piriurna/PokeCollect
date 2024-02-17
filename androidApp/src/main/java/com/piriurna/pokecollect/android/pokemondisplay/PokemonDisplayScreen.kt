package com.piriurna.pokecollect.android.pokemondisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.piriurna.pokecollect.android.MyApplicationTheme
import com.piriurna.pokecollect.domain.models.Pokemon

@Composable
fun PokemonDisplayScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDisplayViewModel
) {
    val uiState = viewModel.uiState.value
    LaunchedEffect(Unit) {
        viewModel.getNextPokemon()
    }

    PokemonDisplayScreenContent(modifier, uiState, viewModel::getNextPokemon)
}

@Composable
private fun PokemonDisplayScreenContent(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    onNextPokemonLoadClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when {
            uiState.isLoading -> {
                Text(text = "Finding your next Pokemon...")
            }
            else -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PokemonEncounterContainer(
                        modifier = Modifier.weight(1.5f),
                        uiState = uiState,
                        onNextPokemonLoadClicked = onNextPokemonLoadClicked
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Red)
                            .fillMaxSize()
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonEncounterContainer(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    onNextPokemonLoadClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        uiState.currentPokemon?.imageUrl?.let {
            AsyncImage(
                modifier = Modifier.size(120.dp),
                model = uiState.currentPokemon.imageUrl,
                contentDescription = "${uiState.currentPokemon.name} image"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = uiState.currentPokemon.name)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = onNextPokemonLoadClicked) {
            Text(text = "Load Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PokemonDisplayScreenPreview() {
    MyApplicationTheme {
        PokemonDisplayScreenContent(
            uiState = PokemonDisplayUiState(
                currentPokemon = Pokemon(
                    id = 0,
                    name = "aa",
                    seen = false,
                    imageUrl = "",
                    type = ""
                )
            )
        )
    }
}