package com.piriurna.pokecollect.data.database

import com.piriurna.pokecollect.cache.AppDatabase
import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.mappers.toDto

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

    internal fun createPokemons(pokemons: List<PokemonResponse>) {
        dbQuery.transaction {
            pokemons.forEach { launch ->
                insertPokemon(launch.toDto())
            }
        }
    }

    private fun insertPokemon(pokemon: PokemonDto) {
        dbQuery.insertPokemon(
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            kind = pokemon.kind,
        )
    }

    private fun mapPokemonSelecting(
        id: Long,
        name: String,
        imageUrl: String,
        kind: String
    ): PokemonDto {
        return PokemonDto(
            id = id,
            name = name,
            imageUrl = imageUrl,
            kind = kind
        )
    }
}