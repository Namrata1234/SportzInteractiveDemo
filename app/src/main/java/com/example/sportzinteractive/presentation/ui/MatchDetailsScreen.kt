package com.example.sportzinteractive.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportzinteractive.utils.ApiState

import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(navController: NavController, viewModel: MatchViewModel= hiltViewModel()) {
    val matchDetails by viewModel.matchDetails.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Match Details") }) }) {
        when (matchDetails) {
            is ApiState.Loading -> LoadingScreen()
            is ApiState.Success -> {
                val match = (matchDetails as ApiState.Success).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Log.d("matchData",match.toString())
                    Text(text = "${match.matchDetail.teamHome} vs ${match.matchDetail.teamAway}",
                        style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = "Date: ${match.matchDetail.match.date}")
                    Text(text = "Venue: ${match.matchDetail.venue.name}")
                    Button(onClick = { navController.navigate("squad_screen") }) {
                        Text(text = "View Squads")
                    }

                }
            }
            is ApiState.Error -> Text(text = (matchDetails as ApiState.Error).message)
            else -> {}
        }
    }
}