package com.piriurna.pokecollect.android.splash.di

import com.piriurna.pokecollect.android.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun splashModule() = module {
    viewModel { SplashViewModel(get(), get()) }
}