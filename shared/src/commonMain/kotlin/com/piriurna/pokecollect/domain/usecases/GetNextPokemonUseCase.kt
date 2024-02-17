package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.mappers.toDto
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class GetNextPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(): Flow<Pokemon?> {
        return flow {
            var unseenPokemonCount = pokemonRepository.getUnseenPokemonCount()
            if (unseenPokemonCount <= 1) {
                val newList = pokemonRepository.fetchNewApiPokemons()
                pokemonRepository.insertAllPokemons(newList.map { it.toDto() })
            }
            unseenPokemonCount = pokemonRepository.getUnseenPokemonCount()
            val randomIndex = Random.nextInt(1, unseenPokemonCount)
            val pokemon = pokemonRepository.getUnseenPokemonAtIndex(randomIndex)?.toDomain()
            emit(pokemon)
        }
    }
}