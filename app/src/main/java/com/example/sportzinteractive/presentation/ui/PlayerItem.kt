package com.example.sportzinteractive.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sportzinteractive.data.model.Player

@Composable
fun PlayerItem(player: Player) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = player.nameFull, fontWeight = FontWeight.Bold)
                if (player.isCaptain == true) Text(text = "(C)", color = Color.Blue)
                if (player.isKeeper == true) Text(text = "(WK)", color = Color.Red)
                Text(text = "Batting: ${player.batting.style}")
                Text(text = "Bowling: ${player.bowling.style}")
            }
        }
    }
}