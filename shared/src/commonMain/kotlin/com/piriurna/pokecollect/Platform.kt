package com.piriurna.pokecollect

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform