package com.piriurna.pokecollect.android.battle

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.CatchPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.battle.BattlePokemonUseCase
import kotlinx.coroutines.launch

data class BattleUiState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val currentTurn: Int = 0
) {

    val playerPokemon = pokemonList.getOrNull(0)
    val enemyPokemon = pokemonList.getOrNull(1)


    val battleEnded = pokemonList.any { it.currentHp == 0 }
}

class BattleViewModel constructor(
    private val battlePokemonUseCase: BattlePokemonUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState: MutableState<BattleUiState> = mutableStateOf(BattleUiState())
    val uiState: State<BattleUiState> = _uiState

    fun setup() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        val playerPokemon = savedStateHandle.get<Int>("attackingPokemon_id")?.let {
             getPokemonUseCase(it)
        }

        val enemyPokemon = savedStateHandle.get<Int>("defendingPokemon_id")?.let {
            getPokemonUseCase(it)
        }

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            pokemonList = listOfNotNull(playerPokemon, enemyPokemon)
        )
    }

    fun battlePokemons() {
        with(_uiState.value) {
            if (battleEnded) return

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                pokemonList = battlePokemonUseCase(pokemonList, currentTurn),
                currentTurn = _uiState.value.currentTurn + 1
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