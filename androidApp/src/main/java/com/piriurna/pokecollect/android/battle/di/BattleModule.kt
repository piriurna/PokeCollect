package com.piriurna.pokecollect.android.battle.di

import com.piriurna.pokecollect.android.battle.BattleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun battleModule() = module {

    viewModel { BattleViewModel(get(), get(), get(), get()) }
}