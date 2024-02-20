package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class GetPokemonUseCase constructor(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(id: Int): Pokemon? {
        return pokemonRepository.getPokemon(id.toLong())?.let {
            if(it.owned == 1){
                pokemonRepository.updateLastSeenPokemon(id.toLong())
            }

            return@let it.toDomain()
        }
    }
}