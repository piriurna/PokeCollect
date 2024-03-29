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

    internal fun getAllPokemons(): List<PokemonDto> {
        return dbQuery.selectAllPokemon(::mapPokemonSelecting).executeAsList()
    }

    internal fun getPokemonsCount(): Int {
        return dbQuery.pokemonCount().executeAsOne().toInt()
    }

    internal fun getOwnedPokemonsCount(): Int {
        return dbQuery.ownedPokemonCount().executeAsOne().toInt()
    }

    internal fun getAllSeenPokemons(coroutineContext: CoroutineContext): Flow<List<PokemonDto>> {
        return dbQuery.selectAllSeenPokemon(::mapPokemonSelecting).asFlow().mapToList(coroutineContext)
    }

    internal fun getWildPokemonCount(): Int {
        return dbQuery.wildPokemonCount().executeAsOne().toInt()
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

    internal fun getPokemonList(ids: List<Long>): List<PokemonDto?> {
        return dbQuery.selectPokemonList(ids, ::mapPokemonSelecting).executeAsList()
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
                owned = owned.toLong(),
                hp = hp.toLong(),
                defensePower = defensePower.toLong(),
                attackPower = attackPower.toLong(),
                lastUsedTimestamp = lastUsedTimestamp
            )
        }
    }

    private fun insertPokemon(pokemon: PokemonDto) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            kind = pokemon.kind,
            seen = pokemon.seen.toLong(),
            owned = pokemon.owned.toLong(),
            hp = pokemon.hp.toLong(),
            defensePower = pokemon.defensePower.toLong(),
            attackPower = pokemon.attackPower.toLong(),
            lastUsedTimestamp = pokemon.lastUsedTimestamp
        )
    }

    private fun mapPokemonSelecting(
        id: Long,
        name: String,
        imageUrl: String,
        kind: String,
        seen: Long,
        owned: Long,
        hp: Long,
        defensePower: Long,
        attackPower: Long,
        lastUsedTimestamp: Long
    ): PokemonDto {
        return PokemonDto(
            id = id,
            name = name,
            imageUrl = imageUrl,
            kind = kind,
            seen = seen.toInt(),
            owned = owned.toInt(),
            hp = hp.toInt(),
            defensePower = defensePower.toInt(),
            attackPower = attackPower.toInt(),
            lastUsedTimestamp = lastUsedTimestamp
        )
    }
}