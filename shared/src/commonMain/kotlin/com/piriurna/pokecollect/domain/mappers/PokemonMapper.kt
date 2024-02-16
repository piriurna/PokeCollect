package com.piriurna.pokecollect.domain.mappers

import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.models.Pokemon

fun PokemonResponse.toDomain(): Pokemon {
    return Pokemon(
            id = this.id,
            name = this.name,
            type = this.types.firstOrNull()?.type?.name?:"",
            imageUrl = this.sprites.frontDefault?:""
        )
}

fun List<PokemonDto>.toDomain(): List<Pokemon> {
    return this.map {
        Pokemon(
            id = it.id,
            name = it.name,
            type = it.kind,
            imageUrl = it.imageUrl
        )
    }
}

fun PokemonResponse.toDto(): PokemonDto {
    return PokemonDto(
        id = 0,
        name = name,
        imageUrl = sprites.frontDefault?:"",
        kind = types.firstOrNull()?.type?.name?:""
    )
}