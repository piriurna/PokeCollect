package com.piriurna.pokecollect.android.pokemondisplay

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.piriurna.pokecollect.android.MyApplicationTheme
import com.piriurna.pokecollect.android.pokemondisplay.ui.components.BattlePoxedexItem
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


@Composable
private fun PokemonDisplayScreenContent(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    onNextPokemonLoadClicked: () -> Unit = {},
    onCatchPokemonPressed: (Pokemon) -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            PokemonEncounterContainer(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(),
                pokemon = uiState.currentPokemon,
                isLoading = uiState.isLoading
            )

            PokedexContainer(
                modifier = Modifier
                    .fillMaxSize(),
                uiState = uiState
            )
        }


        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onNextPokemonLoadClicked, colors = ButtonDefaults.buttonColors()) {
                Text(text = "Flee")
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Versus",
                style = MaterialTheme.typography.headlineSmall,
            )

            Button(onClick = { onCatchPokemonPressed(uiState.currentPokemon!!) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text(text = "Battle")
            }
        }
    }
}

@Composable
private fun PokemonEncounterContainer(
    modifier: Modifier = Modifier,
    pokemon: Pokemon?,
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
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokedexContainer(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState
) {
    HorizontalPager(
        modifier = modifier,
        state = rememberPagerState { uiState.ownedPokemonList.size }
    ) {
        BattlePoxedexItem(
            modifier = Modifier.fillMaxSize(),
            pokemon = uiState.ownedPokemonList[it],
        )
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
                    owned = false,
                    hp = 0,
                    defensePower = 1,
                    attackPower = 1
                )
            )
        )
    }
}