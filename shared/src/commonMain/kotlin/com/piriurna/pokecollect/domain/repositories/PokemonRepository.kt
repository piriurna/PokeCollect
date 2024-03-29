package com.piriurna.pokecollect.domain.repositories

import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface PokemonRepository {

    suspend fun fetchNewApiPokemons(): List<PokemonResponse>

    suspend fun getStoredPokemons(): List<PokemonDto>

    suspend fun getOwnedPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>>

    suspend fun getOwnedPokemonsCount(): Int

    suspend fun getPokemon(id: Long): PokemonDto?

    suspend fun getPokemonList(ids: List<Long>): List<PokemonDto?>

    suspend fun getSeenPokemon(coroutineContext: CoroutineContext): Flow<List<PokemonDto>>

    suspend fun getWildPokemonCount(): Int

    suspend fun getRandomWildPokemon(): PokemonDto?

    suspend fun insertAllPokemons(pokemons: List<PokemonDto>)

    suspend fun setPokemonAsSeen(id: Long)

    suspend fun catchPokemon(pokemon: PokemonDto)

    suspend fun updateLastSeenPokemon(id: Long)
}