package com.piriurna.pokecollect.android.choosestarter

import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.android.ui.common.BaseViewModel
import com.piriurna.pokecollect.android.ui.common.UiState
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.CatchPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.starter.GetStarterPokemonsUseCase
import kotlinx.coroutines.launch

data class ChooseStarterUiState(
    val isLoading: Boolean = false,
    val starterOptions: List<Pokemon> = emptyList()
): UiState

class ChooseStarterViewModel constructor(
    private val getStarterPokemonsUseCase: GetStarterPokemonsUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase
): BaseViewModel<ChooseStarterUiState>() {

    override fun initialState(): ChooseStarterUiState = ChooseStarterUiState()

    init {
        viewModelScope.launch {
            updateState(uiState.value.copy(
                starterOptions = getStarterPokemonsUseCase()
            ))
        }
    }


    fun choosePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            catchPokemonUseCase(pokemon)
        }
    }
}