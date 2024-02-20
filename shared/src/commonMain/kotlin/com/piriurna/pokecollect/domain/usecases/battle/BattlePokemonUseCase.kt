package com.piriurna.pokecollect.domain.usecases.battle

import com.piriurna.pokecollect.domain.models.Pokemon
import kotlin.math.max
import kotlin.math.roundToInt

class BattlePokemonUseCase {
    operator fun invoke(pokemonList: List<Pokemon>, attackingId: Long): List<Pokemon> {
        val attackPokemon = pokemonList.find { it.id == attackingId }
        val defensePokemon = pokemonList.find { it.id != attackingId }

        if (attackPokemon == null || defensePokemon == null) return pokemonList

        val counterMultiplier = when {
            attackPokemon.type == defensePokemon.type.counter -> 2f

            defensePokemon.type == attackPokemon.type.counter -> 0.5f

            else -> 1f
        }

        val damageDealt = when {
            attackPokemon.attackPower > defensePokemon.defensePower -> attackPokemon.attackPower / 10f
            attackPokemon.attackPower == defensePokemon.defensePower -> attackPokemon.attackPower / 20f
            else -> attackPokemon.attackPower / 40f
        }

        val newDefenseHp = max(0f, defensePokemon.currentHp - damageDealt * counterMultiplier)


        return pokemonList.map {
            if(defensePokemon.id == it.id)
                return@map defensePokemon.copy(currentHp = max(0, newDefenseHp.roundToInt()))
            else
                it
        }
    }
}