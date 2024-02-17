package com.piriurna.pokecollect.android.ui.pokemondisplay

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.piriurna.pokecollect.android.PokeCollectApplication
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.usecases.GetNextPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.SetPokemonAsSeenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class PokemonDisplayUiState(
    val currentPokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val hasFetched: Boolean = false
)
class PokemonDisplayViewModel constructor(
    private val getNextPokemonUseCase: GetNextPokemonUseCase,
    private val setPokemonAsSeenUseCase: SetPokemonAsSeenUseCase
): ViewModel() {

    private val _uiState: MutableState<PokemonDisplayUiState> = mutableStateOf(PokemonDisplayUiState())
    val uiState: State<PokemonDisplayUiState> = _uiState

    fun getNextPokemon() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)

            val pokemon = getNextPokemonUseCase().first()

            pokemon?.let {
                setPokemonAsSeenUseCase(it.id)
            }

            _uiState.value = uiState.value.copy(
                currentPokemon = pokemon,
                isLoading = false
            )
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])

                return PokemonDisplayViewModel(
                    getNextPokemonUseCase = (application as PokeCollectApplication).getNextPokemonUseCase,
                    setPokemonAsSeenUseCase = application.setPokemonAsSeenUseCase
                ) as T
            }
        }
    }
}