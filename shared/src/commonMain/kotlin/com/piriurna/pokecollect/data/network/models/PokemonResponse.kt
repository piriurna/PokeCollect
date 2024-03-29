package com.piriurna.pokecollect.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: Sprites,
    @SerialName("types")
    val types: List<Type>,
    @SerialName("stats")
    val stats: List<Stat>
    ) {
    @Serializable
    data class Sprites(
        @SerialName("back_default")
        val backDefault: String?,
        @SerialName("front_default")
        val frontDefault: String?
    )
    @Serializable
    data class Type(
        @SerialName("slot")
        val slot: Int?,
        @SerialName("type")
        val type: PokemonType
    ) {
        @Serializable
        data class PokemonType(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }

    @Serializable
    data class Stat(
        @SerialName("base_stat")
        val baseStat: Int,
        val stat: StatInfo
    ) {
        @Serializable
        data class StatInfo(
            val name: String,
            val url: String
        )
    }
}