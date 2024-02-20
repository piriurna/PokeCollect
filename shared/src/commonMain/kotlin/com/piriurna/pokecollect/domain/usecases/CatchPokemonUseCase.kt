package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.datetime.Clock

class CatchPokemonUseCase constructor(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(pokemon: Pokemon) {
        val currentTimeMillis = Clock.System.now().epochSeconds
        pokemonRepository.catchPokemon(pokemon.toDto(currentTimeMillis))
    }
}