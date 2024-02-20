package com.piriurna.pokecollect.android.battle.navigation

object BattleDestinations {
    private const val BattleHomePage = "battle"
    const val AttackingPokemonIdArg = "attackingPokemon_id"
    const val DefendingPokemonIdArg = "defendingPokemon_id"
    const val BattleHomePageFullRoute = "$BattleHomePage/{$AttackingPokemonIdArg}/{$DefendingPokemonIdArg}"
}