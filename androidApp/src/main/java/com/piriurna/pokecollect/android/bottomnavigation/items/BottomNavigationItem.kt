package com.piriurna.pokecollect.android.bottomnavigation.items

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String,
    val route: String
)
