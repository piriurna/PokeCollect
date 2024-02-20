package com.piriurna.pokecollect.di

import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.data.repositories.PokemonRepositoryImpl
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import com.piriurna.pokecollect.domain.usecases.CatchPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetNextPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetOwnedPokemonsUseCase
import com.piriurna.pokecollect.domain.usecases.GetPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.GetSeenPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.SetPokemonAsSeenUseCase
import com.piriurna.pokecollect.domain.usecases.battle.BattlePokemonUseCase
import org.koin.dsl.module

fun appModule() = module {
    single<PokemonSDK> { PokemonSDK(get()) }

    single<PokemonRepository> { PokemonRepositoryImpl(get()) }

    single { GetNextPokemonUseCase(get()) }

    single { SetPokemonAsSeenUseCase(get()) }

    single { GetOwnedPokemonsUseCase(get()) }

    single { CatchPokemonUseCase(get()) }

    single { GetSeenPokemonUseCase(get()) }

    single { GetPokemonUseCase(get()) }

    single { BattlePokemonUseCase() }
}