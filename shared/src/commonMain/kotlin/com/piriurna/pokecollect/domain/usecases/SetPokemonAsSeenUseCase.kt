package com.piriurna.pokecollect.domain.usecases

import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class SetPokemonAsSeenUseCase(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(id: Long) {
        return pokemonRepository.setPokemonAsSeen(id)
    }
}