package com.piriurna.pokecollect.data.network

import com.piriurna.pokecollect.data.network.models.PokeApiResponse
import com.piriurna.pokecollect.data.network.models.PokemonListItemResponse
import com.piriurna.pokecollect.data.network.models.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllPokemonUrlList(): PokeApiResponse<PokemonListItemResponse> {
        return httpClient
            .get("https://pokeapi.co/api/v2/pokemon/")
            .body()
    }

    suspend fun getPokemonDetails(url: String): PokemonResponse {
        return httpClient.get(url).body()
    }
}