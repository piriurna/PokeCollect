package com.piriurna.pokecollect.database

import com.piriurna.pokecollect.cache.AppDatabase
import com.piriurna.pokecollect.entity.Pokemon

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries


    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllPokemons()
        }
    }

    internal fun getAllPokemons(): List<Pokemon> {
        return dbQuery.selectAllPokemon(::mapPokemonSelecting).executeAsList()
    }

    internal fun createPokemons(pokemons: List<Pokemon>) {
        dbQuery.transaction {
            pokemons.forEach { launch ->
                insertPokemon(launch)
            }
        }
    }

    private fun insertPokemon(pokemon: Pokemon) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            kind = pokemon.kind,
            power = pokemon.power
        )
    }

    private fun mapPokemonSelecting(
        id: Long,
        name: String,
        imageUrl: String,
        kind: String,
        power: Long
    ): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            imageUrl = imageUrl,
            kind = kind,
            power = power
        )
    }
}