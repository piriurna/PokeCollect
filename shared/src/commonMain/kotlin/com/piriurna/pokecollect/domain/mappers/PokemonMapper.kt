package com.piriurna.pokecollect.domain.mappers

import com.piriurna.pokecollect.data.entity.PokemonDto
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import com.piriurna.pokecollect.domain.models.Pokemon
import com.piriurna.pokecollect.domain.models.PokemonType

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        type = convertPokemonType(kind),
        imageUrl = imageUrl,
        seen = seen == 1,
        owned = owned == 1,
        currentHp = hp,
        totalHp = hp,
        defensePower = defensePower,
        attackPower = attackPower
    )
}

fun PokemonResponse.toDto(): PokemonDto {
    return PokemonDto(
        id = id,
        name = name,
        imageUrl = sprites.frontDefault?:"",
        kind = types.firstOrNull()?.type?.name?:"",
        seen = 0,
        owned = 0,
        hp = stats.firstOrNull { it.stat.name == "hp" }?.baseStat?:0,
        defensePower = stats.firstOrNull { it.stat.name == "defense" }?.baseStat?:0,
        attackPower = stats.firstOrNull { it.stat.name == "attack" }?.baseStat?:0,
        lastUsedTimestamp = 0
    )
}

fun Pokemon.toDto(lastTimeUsedTimestamp: Long): PokemonDto {
    return PokemonDto(
        id = id,
        name = name,
        imageUrl = imageUrl,
        kind = type.name,
        seen = if(seen) 1 else 0,
        owned = if (owned) 1 else 0,
        hp = totalHp,
        defensePower = defensePower,
        attackPower = attackPower,
        lastUsedTimestamp = lastTimeUsedTimestamp
    )
}

private fun convertPokemonType(type: String): PokemonType {
    return when(type) {
        "fire" -> PokemonType.Fire

        "grass" -> PokemonType.Earth

        "water" -> PokemonType.Water

        else -> PokemonType.Unknown
    }
}