package com.shahin.cleancompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shahin.cleancompose.presentation.albums.ArtistAlbumsScreen
import com.shahin.cleancompose.presentation.searchArtists.SearchArtistScreen
import com.shahin.cleancompose.ui.theme.CleanComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SearchArtists.route
                    ) {
                        composable(Screen.SearchArtists.route) {
                            SearchArtistScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.ArtistAlbums.route + "/{artistId}",
                            arguments = listOf(
                                navArgument("artistId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            ArtistAlbumsScreen(
                                artistId = backStackEntry.arguments?.getString("artistId")
                            )
                        }
                    }
                }
            }
        }
    }
}

