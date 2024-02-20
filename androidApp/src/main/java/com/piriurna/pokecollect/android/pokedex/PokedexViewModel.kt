package com.piriurna.pokecollect.android.pokedex

import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.android.ui.common.BaseViewModel
import com.piriurna.pokecollect.android.ui.common.UiState
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.GetSeenPokemonUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class PokedexUiState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList()
): UiState

class PokedexViewModel constructor(
    private val getSeenPokemonUseCase: GetSeenPokemonUseCase
): BaseViewModel<PokedexUiState>() {

    override fun initialState() = PokedexUiState()

    init {
        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))
            getSeenPokemonUseCase(viewModelScope.coroutineContext).collectLatest {
                updateState(
                    uiState.value.copy(
                        isLoading = false,
                        pokemonList = it
                    )
                )
            }
        }
    }
}