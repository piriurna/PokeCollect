package com.piriurna.pokecollect.domain.usecases.splash

import com.piriurna.pokecollect.domain.destinations.Destination
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class GetInitialScreenUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): Destination {
        return when {
            pokemonRepository.getOwnedPokemonsCount() <= 0 -> Destination.StarterSelection

            else -> Destination.WildEncounterScreen
        }
    }
}