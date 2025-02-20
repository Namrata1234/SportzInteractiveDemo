import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.presentation.ui.LoadingScreen

import com.example.sportzinteractive.presentation.ui.SquadFilterRow
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel
import com.example.sportzinteractive.utils.ApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SquadScreen(viewModel: MatchViewModel = hiltViewModel()) {
    val matchDetails by viewModel.matchDetails.collectAsState()
    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Squad Details") }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Squad Filter Row
            SquadFilterRow(selectedFilter) { selectedFilter = it }

            when (matchDetails) {
                is ApiState.Loading -> LoadingScreen()
                is ApiState.Success<*> -> {
                    val teams = (matchDetails as ApiState.Success<MatchData>).data.teams
                    val teamAId = "4"  // India
                    val teamBId = "5"  // New Zealand

                    val allPlayers = teams.values.flatMap { it.players.values }
                    val filteredPlayers = when (selectedFilter) {
                        "Team A" -> teams[teamAId]?.players?.values ?: emptyList()
                        "Team B" -> teams[teamBId]?.players?.values ?: emptyList()
                        else -> allPlayers
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        filteredPlayers.groupBy { it.nameFull }.forEach { (teamName, players) ->
                            item {
                                Text(
                                    text = teamName,
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            items(players) { player ->
                                PlayerItem(player)
                            }
                        }
                    }
                }
                is ApiState.Error -> Text(
                    text = (matchDetails as ApiState.Error).message,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }
        }
    }
}
