package com.piriurna.pokecollect.domain.usecases.battle

import com.piriurna.pokecollect.domain.models.Pokemon
import kotlin.math.max

class BattlePokemonUseCase {
    operator fun invoke(pokemonList: List<Pokemon>, currentTurn: Int): List<Pokemon> {
        val attackPokemon = pokemonList[if(currentTurn % 2 == 0) 0 else 1]
        val defensePokemon = pokemonList[if(currentTurn % 2 == 0) 1 else 0]
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