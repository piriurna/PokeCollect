package com.piriurna.pokecollect.android.ui.pokemondisplay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiState.currentPokemon?.imageUrl?.let {
                AsyncImage(
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