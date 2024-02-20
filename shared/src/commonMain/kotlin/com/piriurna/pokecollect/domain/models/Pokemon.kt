package com.piriurna.pokecollect.domain.models

data class Pokemon(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val type: String,
    val seen: Boolean,
    val owned: Boolean,
    val totalHp: Int,
    val currentHp: Int,
    val defensePower: Int,
    val attackPower: Int
) {
    fun isAlive() = currentHp != 0
}