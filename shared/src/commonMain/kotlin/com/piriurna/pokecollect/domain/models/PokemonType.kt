package com.piriurna.pokecollect.domain.models

sealed class PokemonType(val name: String, val counter: PokemonType?) {

    data object Fire: PokemonType("fire", Water)

    data object Water: PokemonType("water", Earth)

    data object Earth: PokemonType("grass", Fire)

    data object Unknown: PokemonType("", null)
}