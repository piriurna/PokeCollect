package com.piriurna.pokecollect

import com.piriurna.pokecollect.data.database.Database
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.data.network.PokemonApi
import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon

class PokemonSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = PokemonApi()

    @Throws(Exception::class)
    suspend fun getPokemons(forceReload: Boolean): List<Pokemon> {
        val cachedPokemons = database.getAllPokemons()
        return if (cachedPokemons.isNotEmpty() && !forceReload) {
            cachedPokemons.toDomain()
        } else {
            val pokemons = api
                .getAllPokemonUrlList()
                .results
                .map {
                    api.getPokemonDetails(it.url)
                }

            database.clearDatabase()
            database.createPokemons(pokemons)

            pokemons.map { it.toDomain() }
        }
    }
}