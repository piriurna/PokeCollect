package com.piriurna.pokecollect.android.pokedex

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.GetOwnedPokemonsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class PokedexUiState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList()
)

class PokedexViewModel constructor(
    private val getOwnedPokemonsUseCase: GetOwnedPokemonsUseCase
): ViewModel() {

    private val _uiState: MutableState<PokedexUiState> = mutableStateOf(PokedexUiState())
    val uiState: State<PokedexUiState> = _uiState

    fun setupScreen() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            getOwnedPokemonsUseCase(viewModelScope.coroutineContext).collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    pokemonList = it
                )
            }
        }
    }
}