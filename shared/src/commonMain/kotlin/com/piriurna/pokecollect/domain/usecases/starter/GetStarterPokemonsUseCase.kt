package com.piriurna.pokecollect.domain.usecases.starter

import com.piriurna.pokecollect.domain.mappers.toDomain
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.repositories.PokemonRepository

class GetStarterPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(): List<Pokemon> {
        return pokemonRepository.getPokemonList(listOf(1, 4, 7)).mapNotNull { it?.toDomain() }
    }
}