package com.plcoding.jetpackcomposepokedex

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.jetpackcomposepokedex.PokedomDetail.pokemonDetailscreen
import com.plcoding.jetpackcomposepokedex.PokemonList.pokemonListScreen
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navcontroler= rememberNavController()
                NavHost(navController =navcontroler,
                     startDestination ="pokemon_list_screen"
                    , builder ={
                        composable("pokemon_list_screen"){
                            pokemonListScreen(navController = navcontroler)
                        }

                        composable("pokemon_detail_screen/{dominantColor}/{pokemonName}",
                            arguments = listOf(
                                navArgument("dominantColor"){
                                    type= NavType.IntType
                                },
                                navArgument("pokemonName"){
                                    type=NavType.StringType
                                }
                            )

                        ){

                            val domainantColor= remember {
                                val color= it.arguments?.getInt("dominantColor")
                                color?.let{ Color(it) }?:Color.White
                            }

                            val pokemonName= remember {
                                it.arguments?.getString("pokemonName")
                            }

                            pokemonDetailscreen(
                                dominantColor = domainantColor,
                                pokemonName = pokemonName?.lowercase() ?: "",
                                navController = navcontroler
                            )

                        }
                    }
                )
            }
        }
    }
}
