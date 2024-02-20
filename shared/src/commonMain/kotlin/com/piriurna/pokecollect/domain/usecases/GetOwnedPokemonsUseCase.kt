package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class GetOwnedPokemonsUseCase constructor(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(coroutineContext: CoroutineContext): Flow<List<Pokemon>> {
        return pokemonRepository
            .getOwnedPokemons(coroutineContext)
            .map { pokemonList ->
                pokemonList.map { it.toDomain() }
            }
    }
}