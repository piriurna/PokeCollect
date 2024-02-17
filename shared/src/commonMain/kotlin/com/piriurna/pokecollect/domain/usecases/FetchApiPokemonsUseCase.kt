package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchApiPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(): Flow<Unit> {
        return flow {
            val pokemons = pokemonRepository.fetchNewApiPokemons()

            pokemonRepository.fetchNewApiPokemons()

            pokemonRepository.insertAllPokemons(pokemons.map { it.toDto() })

            emit(Unit)
        }
    }
}