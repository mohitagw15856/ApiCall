package com.example.music.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.music.app.presentation.song_list.SongListScreen
import com.example.music.app.presentation.song_list.components.NavigationItem
import com.example.music.app.presentation.theme.SongsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.music.R

@AndroidEntryPoint
class SongListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SongsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenType.SongListScreen.route
                    ) {
                        composable(
                            route = ScreenType.SongListScreen.route
                        ) {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController)
            }
        }
    }

    @Composable
    fun HomeScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .wrapContentSize(Alignment.Center)
        ) {
            SongListScreen()
        }
    }

    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen()
            }
        }
    }

}