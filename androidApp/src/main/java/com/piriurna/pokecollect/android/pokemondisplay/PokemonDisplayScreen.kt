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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.piriurna.pokecollect.android.R
import com.piriurna.pokecollect.android.pokemondisplay.ui.components.BattlePoxedexItem
import com.piriurna.pokecollect.android.pokemondisplay.ui.theme.DisplayScreenDimensions.DisplayScreenPokemonImageSize
import com.piriurna.pokecollect.android.pokemondisplay.ui.theme.DisplayScreenDimensions.PokemonEncounterFraction
import com.piriurna.pokecollect.android.ui.theme.AppTheme
import com.piriurna.pokecollect.android.ui.theme.spacing
import com.piriurna.pokecollect.domain.models.Pokemon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDisplayScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PokemonDisplayViewModel
) {
    val uiState = viewModel.uiState.value

    val pokedexPagerState = rememberPagerState { uiState.ownedPokemonList.size }

    PokemonDisplayScreenContent(
        modifier = modifier,
        uiState = uiState,
        onNextPokemonLoadClicked = viewModel::getNextPokemon,
        onCatchPokemonPressed = { navController.navigate(
            "battle/${uiState.ownedPokemonList[pokedexPagerState.currentPage].id}/${uiState.currentPokemon?.id}")
        },
        pagerState = pokedexPagerState
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokemonDisplayScreenContent(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    onNextPokemonLoadClicked: () -> Unit = {},
    onCatchPokemonPressed: (Pokemon) -> Unit = {},
    pagerState: PagerState = rememberPagerState { uiState.ownedPokemonList.size }
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            PokemonEncounterContainer(
                modifier = Modifier
                    .fillMaxHeight(PokemonEncounterFraction)
                    .fillMaxWidth(),
                pokemon = uiState.currentPokemon,
                isLoading = uiState.isLoading
            )

            PokedexContainer(
                modifier = Modifier
                    .fillMaxSize(),
                uiState = uiState,
                pagerState = pagerState
            )
        }


        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onNextPokemonLoadClicked, colors = ButtonDefaults.buttonColors()) {
                Text(text = stringResource(R.string.flee))
            }

            Text(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
                text = stringResource(R.string.versus),
                style = MaterialTheme.typography.headlineSmall,
            )

            Button(onClick = { onCatchPokemonPressed(uiState.currentPokemon!!) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text(text = stringResource(R.string.battle))
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
                Text(text = stringResource(R.string.finding_your_next_pokemon))
            }
            else -> {
                pokemon?.let {
                    AsyncImage(
                        modifier = Modifier.size(DisplayScreenPokemonImageSize),
                        model = it.imageUrl,
                        contentDescription = stringResource(R.string.pokemon_image, it.name)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                    Text(text = it.name)
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokedexContainer(
    modifier: Modifier = Modifier,
    uiState: PokemonDisplayUiState,
    pagerState: PagerState
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        BattlePoxedexItem(
            modifier = Modifier.fillMaxSize(),
            pokemon = uiState.ownedPokemonList[it],
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun PokemonDisplayScreenPreview() {
    AppTheme {
        PokemonDisplayScreenContent(
            uiState = PokemonDisplayUiState(
                currentPokemon = Pokemon(
                    id = 0,
                    name = "aa",
                    seen = false,
                    imageUrl = "",
                    type = "",
                    owned = false,
                    currentHp = 0,
                    totalHp = 0,
                    defensePower = 1,
                    attackPower = 1
                )
            )
        )
    }
}