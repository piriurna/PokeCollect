package com.piriurna.pokecollect.domain.mappers

import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.models.Pokemon

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        type = kind,
        imageUrl = imageUrl,
        seen = seen == 1,
        owned = owned == 1
    )
}

fun PokemonResponse.toDto(): PokemonDto {
    return PokemonDto(
        id = id,
        name = name,
        imageUrl = sprites.frontDefault?:"",
        kind = types.firstOrNull()?.type?.name?:"",
        seen = 0,
        owned = 0
    )
}

fun Pokemon.toDto(): PokemonDto {
    return PokemonDto(
        id = id,
        name = name,
        imageUrl = imageUrl,
        kind = type,
        seen = if(seen) 1 else 0,
        owned = if (owned) 1 else 0
    )
}