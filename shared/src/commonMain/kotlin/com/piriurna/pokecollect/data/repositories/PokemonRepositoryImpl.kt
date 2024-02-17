package com.piriurna.pokecollect.data.repositories

import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
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

    override suspend fun getPokemon(id: Long): PokemonDto? {
        return pokemonSDK.getPokemon(id)
    }

    override suspend fun getUnseenPokemonAtIndex(index: Int): PokemonDto? {
        return pokemonSDK.getUnseenPokemons().elementAtOrNull(index)
    }
    override suspend fun getUnseenPokemonCount(): Int {
        return pokemonSDK.getUnseenPokemonsCount()
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
}