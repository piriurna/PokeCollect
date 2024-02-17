package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class CatchPokemonUseCase constructor(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(pokemon: Pokemon) {
        pokemonRepository.catchPokemon(pokemon.toDto())
    }
}