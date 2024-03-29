package com.piriurna.pokecollect.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("kind")
    val kind: String,
    @SerialName("seen")
    val seen: Int,
    @SerialName("owned")
    val owned: Int,
    @SerialName("hp")
    val hp: Int,
    @SerialName("defensePower")
    val defensePower: Int,
    @SerialName("attackPower")
    val attackPower: Int,
    @SerialName("lastUsedTimestamp")
    val lastUsedTimestamp: Long
)