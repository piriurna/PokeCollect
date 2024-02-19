package com.piriurna.pokecollect.data.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.piriurna.pokecollect.cache.AppDatabase
import com.piriurna.pokecollect.data.entity.PokemonDto
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

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

    internal fun getAllSeenPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return dbQuery.selectAllSeenPokemon(::mapPokemonSelecting).asFlow().mapToList(coroutineContext)
    }

    internal fun getAllOwnedPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return dbQuery.selectOwnedPokemon(::mapPokemonSelecting).asFlow().mapToList(coroutineContext)
    }

    internal fun getRandomWildPokemons(): PokemonDto? {
        return dbQuery.randomWildPokemon(::mapPokemonSelecting).executeAsOneOrNull()
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
                seen = seen.toLong(),
                owned = owned.toLong()
            )
        }
    }

    private fun insertPokemon(pokemon: PokemonDto) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            kind = pokemon.kind,
            seen = 0,
            owned = 0
        )
    }

    private fun mapPokemonSelecting(
        id: Long,
        name: String,
        imageUrl: String,
        kind: String,
        seen: Long,
        owned: Long
    ): PokemonDto {
        return PokemonDto(
            id = id,
            name = name,
            imageUrl = imageUrl,
            kind = kind,
            seen = seen.toInt(),
            owned = owned.toInt()
        )
    }
}