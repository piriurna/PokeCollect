package com.piriurna.pokecollect.domain.destinations

sealed class Destination(val route: String, val args: Map<String, String> = emptyMap()) {

    data object WildEncounterScreen: Destination("wild_encounter_screen")

    data object PokedexScreen: Destination("pokedex_screen")

    data object StarterSelection: Destination("starter_selection_screen")

    data object BattleScreen: Destination(
        "battle_screen",
        args = emptyMap()
    )

    data object SplashScreen: Destination("splash_screen")
}