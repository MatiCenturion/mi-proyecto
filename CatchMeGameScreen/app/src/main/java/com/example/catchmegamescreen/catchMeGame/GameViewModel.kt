package com.example.catchmegamescreen.catchMeGame

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(private val context: Context) : ViewModel() {

    // Estados observables para actualizar la UI
    var timeLeft by mutableStateOf(30)
        private set
    var score by mutableStateOf(0)
        private set
    var gameRunning by mutableStateOf(false)
        private set
    var ballOffset by mutableStateOf(Offset.Zero)
        private set

    // Tamaño del área de juego (en píxeles)
    var gameAreaSize by mutableStateOf(IntSize.Zero)
        private set
    // Tamaño fijo de la bola en píxeles
    val ballSize: Int = 50

    // SharedPreferences para guardar los puntajes
    private val prefs: SharedPreferences =
        context.getSharedPreferences("GameScores", Context.MODE_PRIVATE)

    fun startGame() {
        // Reinicia los estados
        timeLeft = 30
        score = 0
        gameRunning = true

        // Corrutina para la cuenta regresiva
        viewModelScope.launch {
            while (timeLeft > 0 && gameRunning) {
                delay(1000L)
                timeLeft--
            }
            gameRunning = false
            saveScore()
        }

        // Corrutina para actualizar la posición de la bola
        viewModelScope.launch {
            while (gameRunning) {
                delay(1500L)
                updateBallPosition()
            }
        }
    }

    // Actualiza la posición de la bola dentro del área de juego
    private fun updateBallPosition() {
        if (gameAreaSize.width > 0 && gameAreaSize.height > 0) {
            val maxX = (gameAreaSize.width - ballSize).coerceAtLeast(0)
            val maxY = (gameAreaSize.height - ballSize).coerceAtLeast(0)
            val randomX = Random.nextInt(0, maxX).toFloat()
            val randomY = Random.nextInt(0, maxY).toFloat()
            ballOffset = Offset(randomX, randomY)
        }
    }

    // Se invoca cuando se toca la bola
    fun onBallClicked() {
        if (gameRunning && timeLeft > 0) {
            score++
        }
    }

    // Actualiza el tamaño del área de juego desde la UI
    fun updateGameAreaSize(size: IntSize) {
        gameAreaSize = size
    }

    // Guarda el puntaje actual en los top 5 usando SharedPreferences
    private fun saveScore() {
        val scores = prefs.getStringSet("topScores", mutableSetOf())?.toMutableList() ?: mutableListOf()
        scores.add(score.toString())
        val topScores = scores.map { it.toInt() }.sortedDescending().take(5)
        val updatedScores = topScores.map { it.toString() }.toSet()
        prefs.edit().putStringSet("topScores", updatedScores).apply()
    }
}
