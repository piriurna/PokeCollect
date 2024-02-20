package com.piriurna.pokecollect.android.pokedex.di

import com.piriurna.pokecollect.android.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun pokedexModule() = module {
    viewModel { PokedexViewModel(get()) }
}