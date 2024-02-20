package com.piriurna.pokecollect.data.repositories

import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlin.coroutines.CoroutineContext

class PokemonRepositoryImpl constructor(
    private val pokemonSDK: PokemonSDK
): PokemonRepository {
    override suspend fun fetchNewApiPokemons(): List<PokemonResponse> {
        val currentOffset = pokemonSDK.getStoredPokemonsCount()
        return pokemonSDK.getApiPokemons(currentOffset)
    }

    override suspend fun getStoredPokemons(): List<PokemonDto> {
        return pokemonSDK.getStoredPokemons()
    }

    override suspend fun getOwnedPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return pokemonSDK.getOwnedPokemons(coroutineContext)
    }

    override suspend fun getOwnedPokemonsCount(): Int {
        return pokemonSDK.getOwnedPokemonsCount()
    }

    override suspend fun getPokemon(id: Long): PokemonDto? {
        return pokemonSDK.getPokemon(id)
    }

    override suspend fun getPokemonList(ids: List<Long>): List<PokemonDto?> {
        return pokemonSDK.getPokemonList(ids)
    }

    override suspend fun getSeenPokemon(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return pokemonSDK.getSeenPokemons(coroutineContext)
    }

    override suspend fun getWildPokemonCount(): Int {
        return pokemonSDK.getWildPokemonCount()
    }

    override suspend fun getRandomWildPokemon(): PokemonDto? {
        return pokemonSDK.getRandomWildPokemon()
    }
    override suspend fun insertAllPokemons(pokemons: List<PokemonDto>) {
        pokemonSDK.insertAllPokemons(pokemons)
    }

    override suspend fun setPokemonAsSeen(id: Long) {
        val pokemonToUpdate = pokemonSDK.getPokemon(id)?.copy(seen = 1)

        pokemonToUpdate?.let {
            pokemonSDK.updatePokemon(it)
        }
    }

    override suspend fun catchPokemon(pokemon: PokemonDto) {
        pokemonSDK.updatePokemon(pokemon.copy(owned = 1, seen = 1))
    }

    override suspend fun updateLastSeenPokemon(id: Long) {
        val currentTimeMillis = Clock.System.now().epochSeconds
        val pokemonToUpdate =
            pokemonSDK.getPokemon(id)?.copy(lastUsedTimestamp = currentTimeMillis)

        pokemonToUpdate?.let {
            pokemonSDK.updatePokemon(it)
        }
    }
}