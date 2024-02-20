package com.piriurna.pokecollect.android.pokemondisplay

import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.android.ui.common.BaseViewModel
import com.piriurna.pokecollect.android.ui.common.UiState
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
): UiState

class PokemonDisplayViewModel constructor(
    private val getNextPokemonUseCase: GetNextPokemonUseCase,
    private val setPokemonAsSeenUseCase: SetPokemonAsSeenUseCase,
    private val getOwnedPokemonsUseCase: GetOwnedPokemonsUseCase
): BaseViewModel<PokemonDisplayUiState>() {

    override fun initialState() = PokemonDisplayUiState()

    init {
        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))

            getOwnedPokemonsUseCase(viewModelScope.coroutineContext).collectLatest {
                updateState(uiState.value.copy(ownedPokemonList = it))
            }

        }

        getNextPokemon()
    }

    fun getNextPokemon() {
        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))

            delay(1000)
            
            val pokemon = getNextPokemonUseCase()

            pokemon?.let {
                setPokemonAsSeenUseCase(it.id)
            }

            updateState(
                uiState.value.copy(
                    currentPokemon = pokemon,
                    isLoading = false
                )
            )

        }
    }
}