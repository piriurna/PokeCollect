package com.piriurna.pokecollect.android.pokemondisplay

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.GetNextPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetOwnedPokemonsUseCase
import com.piriurna.pokecollect.domain.usecases.SetPokemonAsSeenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class PokemonDisplayUiState(
    val currentPokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val hasFetched: Boolean = false,
    val ownedPokemonList: List<Pokemon> = emptyList()
)
class PokemonDisplayViewModel constructor(
    private val getNextPokemonUseCase: GetNextPokemonUseCase,
    private val setPokemonAsSeenUseCase: SetPokemonAsSeenUseCase,
    private val getOwnedPokemonsUseCase: GetOwnedPokemonsUseCase
): ViewModel() {

    private val _uiState: MutableState<PokemonDisplayUiState> = mutableStateOf(PokemonDisplayUiState())
    val uiState: State<PokemonDisplayUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            getOwnedPokemonsUseCase(viewModelScope.coroutineContext).collectLatest {
                _uiState.value = _uiState.value.copy(ownedPokemonList = it)
            }

        }

        getNextPokemon()
    }

    fun getNextPokemon() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            delay(1000)
            
            val pokemon = getNextPokemonUseCase()

            pokemon?.let {
                setPokemonAsSeenUseCase(it.id)
            }

            _uiState.value = _uiState.value.copy(
                currentPokemon = pokemon,
                isLoading = false
            )

        }
    }
}