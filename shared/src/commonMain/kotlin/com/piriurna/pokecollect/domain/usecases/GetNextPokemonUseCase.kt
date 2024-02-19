package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class GetNextPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(): Pokemon? {
        var randomWildPokemon = pokemonRepository.getRandomWildPokemon()
        if (randomWildPokemon == null) {
            val newList = pokemonRepository.fetchNewApiPokemons()
            pokemonRepository.insertAllPokemons(newList.map { it.toDto() })
        }
        randomWildPokemon = pokemonRepository.getRandomWildPokemon()

        return randomWildPokemon?.toDomain()
    }
}