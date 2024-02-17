package com.piriurna.pokecollect.android.pokemondisplay

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.piriurna.pokecollect.android.pokedex.ui.components.PokedexItem
import com.piriurna.pokecollect.domain.models.Pokemon

@Composable
fun PokemonDisplayScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDisplayViewModel
) {
    val uiState = viewModel.uiState.value
    LaunchedEffect(Unit) {
        viewModel.setup()
        viewModel.getNextPokemon()
    }

    PokemonDisplayScreenContent(modifier, uiState, viewModel::getNextPokemon, viewModel::catchPokemon)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokemonDisplayScreenContent(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    onNextPokemonLoadClicked: () -> Unit = {},
    onCatchPokemonPressed: (Pokemon) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        PokemonEncounterContainer(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            pokemon = uiState.currentPokemon,
            onNextPokemonLoadClicked = onNextPokemonLoadClicked,
            onCatchPokemonPressed = onCatchPokemonPressed,
            isLoading = uiState.isLoading
        )

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberPagerState { uiState.ownedPokemonList.size }
        ) {
            PokedexItem(modifier = Modifier.weight(1f), pokemon = uiState.ownedPokemonList[it])
        }
    }
}

@Composable
private fun PokemonEncounterContainer(
    modifier: Modifier = Modifier,
    pokemon: Pokemon?,
    onNextPokemonLoadClicked: () -> Unit,
    onCatchPokemonPressed: (Pokemon) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading -> {
                Text(text = "Finding your next Pokemon...")
            }
            else -> {
                pokemon?.let {
                    AsyncImage(
                        modifier = Modifier.size(120.dp),
                        model = it.imageUrl,
                        contentDescription = "${it.name} image"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it.name)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(onClick = onNextPokemonLoadClicked) {
                    Text(text = "Load Next")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { pokemon?.let { onCatchPokemonPressed(pokemon) } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Catch it")
                }
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
                    type = "",
                    owned = false
                )
            )
        )
    }
}