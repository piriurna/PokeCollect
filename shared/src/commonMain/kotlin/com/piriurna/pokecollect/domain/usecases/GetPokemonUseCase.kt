package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon
import kotlinx.datetime.Clock

class GetPokemonUseCase constructor(
    private val pokemonSDK: PokemonSDK
) {


    operator fun invoke(id: Int): Pokemon? {
        return pokemonSDK.getPokemon(id.toLong())?.let {
            if(it.owned == 1){
                val currentTimeMillis = Clock.System.now().epochSeconds
                pokemonSDK.updatePokemon(it.copy(lastUsedTimestamp = currentTimeMillis))
            }

            return@let it.toDomain()
        }
    }
}