package com.piriurna.pokecollect.android.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.piriurna.pokecollect.android.R

@Composable
fun SplashScreen(
    modifier: Modifier,
    viewModel: SplashViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value

    LaunchedEffect(uiState.destination) {
        if (uiState.destination != null) {
            navController.navigate(uiState.destination)
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.fillMaxSize(0.5f),
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = ""
        )
    }
}