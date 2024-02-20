package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon

class GetPokemonUseCase constructor(
    private val pokemonSDK: PokemonSDK
) {


    operator fun invoke(id: Int): Pokemon? {
        return pokemonSDK.getPokemon(id.toLong())?.toDomain()
    }
}