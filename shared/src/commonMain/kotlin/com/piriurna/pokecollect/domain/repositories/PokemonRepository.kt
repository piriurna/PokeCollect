package com.piriurna.pokecollect.domain.repositories

import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface PokemonRepository {

    suspend fun fetchNewApiPokemons(): List<PokemonResponse>

    suspend fun getStoredPokemons(): List<PokemonDto>

    suspend fun getOwnedPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>>

    suspend fun getPokemon(id: Long): PokemonDto?

    suspend fun getUnseenPokemonAtIndex(index: Int): PokemonDto?

    suspend fun getUnseenPokemonCount(): Int

    suspend fun insertAllPokemons(pokemons: List<PokemonDto>)

    suspend fun setPokemonAsSeen(id: Long)

    suspend fun catchPokemon(pokemon: PokemonDto)
}