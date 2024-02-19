package com.piriurna.pokecollect.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.piriurna.pokecollect.android.pokemondisplay.PokemonDisplayViewModel
import com.piriurna.pokecollect.android.ui.PokeCollectNavHost
import com.piriurna.pokecollect.android.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PokeCollectNavHost(
                    navHostController = rememberNavController(),
                    pokemonDisplayViewModel = koinViewModel<PokemonDisplayViewModel>()
                )
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
