package com.piriurna.pokecollect.android

import android.app.Application
import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.data.repositories.PokemonRepositoryImpl
import com.piriurna.pokecollect.domain.usecases.FetchApiPokemonsUseCase
import com.piriurna.pokecollect.domain.usecases.GetNextPokemonUseCase
import com.piriurna.pokecollect.domain.usecases.SetPokemonAsSeenUseCase

class PokeCollectApplication: Application() {

    val pokemonSDK = PokemonSDK(DatabaseDriverFactory(this))
    val pokemonRepository = PokemonRepositoryImpl(pokemonSDK)
    val getNextPokemonUseCase = GetNextPokemonUseCase(pokemonRepository)
    val setPokemonAsSeenUseCase = SetPokemonAsSeenUseCase(pokemonRepository)
}