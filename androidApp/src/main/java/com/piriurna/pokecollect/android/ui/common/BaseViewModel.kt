package com.piriurna.pokecollect.android.ui.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

interface UiState

abstract class BaseViewModel<T: UiState>: ViewModel() {

    abstract fun initialState(): T

    private val _uiState: MutableState<T> by lazy { mutableStateOf(initialState()) }
    val uiState: State<T> = _uiState

    protected fun updateState(uiState: T) {
        _uiState.value = uiState
    }
}