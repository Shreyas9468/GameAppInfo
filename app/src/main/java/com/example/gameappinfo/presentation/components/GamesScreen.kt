package com.example.gameappinfo.presentation.components


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.gameappinfo.domain.model.Games
import com.example.gameappinfo.presentation.state.GamesState
import com.example.gameappinfo.presentation.viewmodel.GameViewModel

import androidx.compose.runtime.*

@Composable
fun GamesScreen(
    gameViewModel: GameViewModel,
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val gamesState = gameViewModel.stategames.collectAsState()

    when {
        gamesState.value.isLoading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        !gamesState.value.error.isNullOrBlank() -> {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = gamesState.value.error!!,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        gamesState.value.games?.isNotEmpty() == true -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gamesState.value.games!!) { game ->
                    GameItem(
                        game = game,
                        onClick = {
                            onGameClick(game.id)  // Navigate to GameScreen with game ID
                        }
                    )
                }
            }
        }
        else -> {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = "No games available.",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
        }
    }
}



@Composable
fun GameItem(game: Games,  onClick: () -> Unit,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(game.thumbnail),
                contentDescription = game.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = game.genre,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = game.short_description,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Release Date: ${game.release_date}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
            }
        }
    }
}
