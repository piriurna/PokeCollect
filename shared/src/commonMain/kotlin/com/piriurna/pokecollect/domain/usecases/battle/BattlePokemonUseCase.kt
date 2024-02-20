package com.piriurna.pokecollect.domain.usecases.battle

import com.piriurna.pokecollect.domain.models.Pokemon
import kotlin.math.max

class BattlePokemonUseCase {
    operator fun invoke(pokemonList: List<Pokemon>, attackingId: Long): List<Pokemon> {
        val attackPokemon = pokemonList.find { it.id == attackingId }
        val defensePokemon = pokemonList.find { it.id != attackingId }

        if (attackPokemon == null || defensePokemon == null) return pokemonList

        val newDefenseHp = defensePokemon.currentHp - when {
            attackPokemon.attackPower > defensePokemon.defensePower -> attackPokemon.attackPower / 10
            attackPokemon.attackPower == defensePokemon.defensePower -> attackPokemon.attackPower / 20
            else -> attackPokemon.attackPower / 40
        }

        return pokemonList.map {
            if(defensePokemon.id == it.id)
                return@map defensePokemon.copy(currentHp = max(0, newDefenseHp))
            else
                it
        }
    }
}