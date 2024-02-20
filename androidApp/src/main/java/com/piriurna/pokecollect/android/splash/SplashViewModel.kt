package com.piriurna.pokecollect.android.splash

import androidx.lifecycle.viewModelScope
import com.piriurna.pokecollect.android.ui.common.BaseViewModel
import com.piriurna.pokecollect.android.ui.common.UiState
import com.piriurna.pokecollect.domain.usecases.UpdateDatabaseUseCase
import com.piriurna.pokecollect.domain.usecases.splash.GetInitialScreenUseCase
import kotlinx.coroutines.launch


data class SplashUiState(
    val isLoading: Boolean = true,
    val destination: String? = null
): UiState


class SplashViewModel(
    updateDatabaseUseCase: UpdateDatabaseUseCase,
    getInitialScreenUseCase: GetInitialScreenUseCase
): BaseViewModel<SplashUiState>() {
    override fun initialState() = SplashUiState()

    init {
        viewModelScope.launch {
            updateDatabaseUseCase()

            updateState(uiState.value.copy(isLoading = false, destination = getInitialScreenUseCase().route))
        }
    }
}