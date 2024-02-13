package com.abhishek.rickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.abhishek.networking.KtorClient
import com.abhishek.rickmorty.screens.CharacterDetailsScreen
import com.abhishek.rickmorty.ui.theme.RickPrimary
import com.abhishek.rickmorty.ui.theme.RickMortyTheme

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = RickPrimary
                ) {
                    CharacterDetailsScreen(
                        ktorClient = ktorClient,
                        characterId = 1
                    )
                }
            }
        }
    }
}