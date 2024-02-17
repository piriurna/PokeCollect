package com.piriurna.pokecollect.android

import android.app.Application
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayViewModel
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class PokeCollectApplication: Application() {
    private val androidModule = module {
        single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }

        viewModel { PokemonDisplayViewModel(get(), get(), get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeCollectApplication)
            // androidModule: refers to Android-specific dependencies
            // appModule: refers to Shared module dependencies
            modules(appModule() + androidModule)
        }
    }
}