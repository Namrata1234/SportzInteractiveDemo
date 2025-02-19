package com.example.sportzinteractive

import SquadScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportzinteractive.presentation.ui.MatchDetailsScreen
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                App()

        }
    }
}


@Composable
fun App(viewModel: MatchViewModel = hiltViewModel()){
    val navController= rememberNavController()
    NavHost(navController, startDestination = "match_details") {
        composable("match_details") { MatchDetailsScreen(navController,viewModel) }
        composable("squad_screen") { SquadScreen(viewModel) }
    }
}
