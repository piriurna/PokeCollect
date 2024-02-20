package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class UpdateDatabaseUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke() {
        if(pokemonRepository.getWildPokemonCount() > 0) return

        val pokemons =  pokemonRepository.fetchNewApiPokemons()

        pokemonRepository.insertAllPokemons(pokemons = pokemons.map { it.toDto() })
    }
}