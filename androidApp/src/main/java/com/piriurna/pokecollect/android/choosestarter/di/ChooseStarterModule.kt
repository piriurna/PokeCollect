package com.piriurna.pokecollect.android.choosestarter.di

import com.piriurna.pokecollect.android.choosestarter.ChooseStarterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun chooseStarterModule() = module {
    viewModel { ChooseStarterViewModel(get(), get()) }
}