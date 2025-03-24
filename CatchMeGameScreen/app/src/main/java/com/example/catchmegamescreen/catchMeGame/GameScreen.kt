package com.example.catchmegamescreen.catchMeGame

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(viewModel: GameViewModel) {
    // Obtenemos los estados directamente desde el ViewModel
    val timeLeft = viewModel.timeLeft
    val score = viewModel.score
    val ballOffset = viewModel.ballOffset
    val gameRunning = viewModel.gameRunning

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Tiempo: $timeLeft")
        Text(text = "Puntos: $score")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.startGame() }, enabled = !gameRunning) {
            Text(text = "Iniciar Juego")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Área de juego: se mide el tamaño para limitar el movimiento de la bola
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .onSizeChanged { size -> viewModel.updateGameAreaSize(size) }
        ) {
            Ball(
                ballOffset = ballOffset,
                ballSize = viewModel.ballSize.dp,
                onClick = { viewModel.onBallClicked() }
            )
        }
    }
}
