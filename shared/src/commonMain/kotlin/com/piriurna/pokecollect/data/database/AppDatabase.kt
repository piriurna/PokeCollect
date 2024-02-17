package com.piriurna.pokecollect.data.database

import com.piriurna.pokecollect.cache.AppDatabase
import com.piriurna.pokecollect.data.entity.PokemonDto

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries


    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllPokemons()
        }
    }

    internal fun getAllPokemons(): List<PokemonDto> {
        return dbQuery.selectAllPokemon(::mapPokemonSelecting).executeAsList()
    }

    internal fun getPokemonsCount(): Int {
        return dbQuery.pokemonCount().executeAsOne().toInt()
    }

    internal fun getAllUnseenPokemons(): List<PokemonDto> {
        return dbQuery.selectAllUnseenPokemon(::mapPokemonSelecting).executeAsList()
    }

    internal fun getUnseenPokemonsCount(): Int {
        return dbQuery.unseenPokemonCount().executeAsOneOrNull()?.toInt()?:0
    }

    internal fun getPokemon(id: Long): PokemonDto? {
        return dbQuery.selectPokemon(id, ::mapPokemonSelecting).executeAsOneOrNull()
    }

    internal fun createPokemons(pokemons: List<PokemonDto>) {
        dbQuery.transaction {
            pokemons.forEach { launch ->
                insertPokemon(launch)
            }
        }
    }

    internal fun updatePokemon(pokemon: PokemonDto) {
        with(pokemon) {
            dbQuery.updatePokemon(
                id = id,
                name = name,
                imageUrl = imageUrl,
                kind = kind,
                seen = seen.toLong()
            )
        }
    }

    private fun insertPokemon(pokemon: PokemonDto) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            kind = pokemon.kind,
            seen = 0
        )
    }

    private fun mapPokemonSelecting(
        id: Long,
        name: String,
        imageUrl: String,
        kind: String,
        seen: Long
    ): PokemonDto {
        return PokemonDto(
            id = id,
            name = name,
            imageUrl = imageUrl,
            kind = kind,
            seen = seen.toInt()
        )
    }
}