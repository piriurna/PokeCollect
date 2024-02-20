package com.piriurna.pokecollect.android.battle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.android.ui.common.BaseViewModel
import com.piriurna.pokecollect.android.ui.common.UiState
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.CatchPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.battle.BattlePokemonUseCase
import kotlinx.coroutines.launch

data class BattleUiState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val currentTurn: Int = 0
):UiState {

    val playerPokemon = pokemonList.getOrNull(0)
    val enemyPokemon = pokemonList.getOrNull(1)


    val battleEnded = pokemonList.any { it.currentHp == 0 }
}

class BattleViewModel constructor(
    private val battlePokemonUseCase: BattlePokemonUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel<BattleUiState>() {

    override fun initialState() = BattleUiState()

    init {
        updateState(uiState.value.copy(isLoading = true))

        val playerPokemon = savedStateHandle.get<Int>("attackingPokemon_id")?.let {
            getPokemonUseCase(it)

        }

        val enemyPokemon = savedStateHandle.get<Int>("defendingPokemon_id")?.let {
            getPokemonUseCase(it)
        }

        updateState(
            uiState.value.copy(
                isLoading = false,
                pokemonList = listOfNotNull(playerPokemon, enemyPokemon)
            )
        )
    }

    fun battlePokemons() {
        with(uiState.value) {
            if (battleEnded) return

            updateState(
                uiState.value.copy(
                    isLoading = false,
                    pokemonList = battlePokemonUseCase(pokemonList, currentTurn),
                    currentTurn = uiState.value.currentTurn + 1
                )
            )
        }
    }

    fun battleFinished() {
        viewModelScope.launch {
            if(uiState.value.playerPokemon?.isAlive() == true) {
                uiState.value.enemyPokemon?.let {
                    catchPokemonUseCase(it)
                }
            }
        }
    }
}