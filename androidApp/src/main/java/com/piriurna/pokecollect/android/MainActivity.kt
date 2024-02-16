package com.piriurna.pokecollect.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.pokecollect.PokemonSDK
import com.piriurna.pokecollect.data.database.DatabaseDriverFactory
import com.piriurna.pokecollect.domain.models.Pokemon

class MainActivity : ComponentActivity() {
    private val sdk = PokemonSDK(DatabaseDriverFactory(this))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemons = remember { mutableStateOf(emptyList<Pokemon>()) }
            
            LaunchedEffect(Unit) {
                pokemons.value = sdk.getPokemons(true)
            }
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn() {
                        items(pokemons.value) {
                            Row {
                                Text(text = it.name)
                            }
                        }
                    }
                }
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
