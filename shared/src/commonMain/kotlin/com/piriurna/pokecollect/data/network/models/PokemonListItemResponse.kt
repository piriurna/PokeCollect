package com.piriurna.pokecollect.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItemResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)