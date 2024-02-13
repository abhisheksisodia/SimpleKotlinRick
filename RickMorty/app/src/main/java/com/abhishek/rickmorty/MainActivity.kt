package com.abhishek.rickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.abhishek.networking.KtorClient
import com.abhishek.rickmorty.screens.CharacterDetailsScreen
import com.abhishek.rickmorty.screens.CharacterEpisodeScreen
import com.abhishek.rickmorty.ui.theme.RickAction
import com.abhishek.rickmorty.ui.theme.RickPrimary
import com.abhishek.rickmorty.ui.theme.RickMortyTheme

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            RickMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = RickPrimary
                ) {
                    NavHost(navController = navController, startDestination = "character_details") {
                        composable(route = "character_details") {
                            CharacterDetailsScreen(
                                ktorClient = ktorClient,
                                characterId = (0..1000).random()
                            ) {
                                navController.navigate("character_episodes/$it")
                            }
                        }
                        composable(
                            route = "character_episodes/{characterId}",
                            arguments = listOf(navArgument("characterId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val characterId: Int =
                                backStackEntry.arguments?.getInt("characterId") ?: -1
                            CharacterEpisodeScreen(
                                characterId = characterId,
                                ktorClient = ktorClient
                            )
                        }
                    }
                }
            }
        }
    }
}