package com.piriurna.pokecollect

import com.piriurna.pokecollect.data.database.Database
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.PokemonApi
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class PokemonSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = PokemonApi()

    @Throws(Exception::class)
    suspend fun getApiPokemons(offset: Int): List<PokemonResponse> {
        return api
            .getAllPokemonUrlList(offset)
            .results
            .map {
                api.getPokemonDetails(it.url)
            }
    }

    @Throws(Exception::class)
    fun getStoredPokemons(): List<PokemonDto> {
        return database.getAllPokemons()
    }

    @Throws(Exception::class)
    fun getStoredPokemonsCount(): Int {
        return database.getPokemonsCount()
    }

    @Throws(Exception::class)
    fun getUnseenPokemons(): List<PokemonDto> {
        return database.getAllUnseenPokemons()
    }

    @Throws(Exception::class)
    fun getUnseenPokemonsCount(): Int {
        return database.getUnseenPokemonsCount()
    }

    @Throws(Exception::class)
    fun getOwnedPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return database.getAllOwnedPokemons(coroutineContext)
    }

    @Throws(Exception::class)
    fun updatePokemon(pokemonDto: PokemonDto) {
        return database.updatePokemon(pokemonDto)
    }

    @Throws(Exception::class)
    fun getPokemon(id: Long): PokemonDto? {
        return database.getPokemon(id = id)
    }

    @Throws(Exception::class)
    fun insertAllPokemons(pokemons: List<PokemonDto>) {
        database.createPokemons(pokemons)
    }
}