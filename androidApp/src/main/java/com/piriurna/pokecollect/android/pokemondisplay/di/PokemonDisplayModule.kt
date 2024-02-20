package com.piriurna.pokecollect.android.pokemondisplay.di

import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun pokemonDisplayModule() = module {
    viewModel { PokemonDisplayViewModel(get(), get(), get(), get()) }
}