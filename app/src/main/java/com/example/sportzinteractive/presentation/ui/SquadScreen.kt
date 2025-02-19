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

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.utils.ApiState
import com.example.sportzinteractive.presentation.ui.LoadingScreen
import com.example.sportzinteractive.presentation.ui.PlayerItem
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SquadScreen(viewModel: MatchViewModel= hiltViewModel()) {
    val matchDetails by viewModel.matchDetails.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Squad Details") }) }) { paddingValues ->
        when (matchDetails) {
            is ApiState.Loading -> LoadingScreen()
            is ApiState.Success<*> -> {
                val teams = (matchDetails as ApiState.Success<MatchData>).data.teams
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                    teams.forEach { (teamId, team) ->
                        item {
                            Text(
                                text = team.nameFull,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        items(team.players.values.toList()) { player ->
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