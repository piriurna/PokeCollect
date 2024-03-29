package com.piriurna.pokecollect.android

import android.app.Application
import com.piriurna.pokecollect.android.battle.di.battleModule
import com.piriurna.pokecollect.android.choosestarter.di.chooseStarterModule
import com.piriurna.pokecollect.android.pokedex.di.pokedexModule
import com.piriurna.pokecollect.android.pokemondisplay.di.pokemonDisplayModule
import com.piriurna.pokecollect.android.splash.di.splashModule
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class PokeCollectApplication: Application() {
    private val androidModule = module {
        single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeCollectApplication)
            // androidModule: refers to Android-specific dependencies
            // appModule: refers to Shared module dependencies
            modules(
                appModule() +
                        splashModule() +
                        chooseStarterModule() +
                        pokedexModule() +
                        pokemonDisplayModule() +
                        battleModule() +
                        androidModule
            )
        }
    }
}