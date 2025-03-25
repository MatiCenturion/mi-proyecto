package com.example.catchmegamescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.catchmegamescreen.catchMeGame.GameScreen
import com.example.catchmegamescreen.catchMeGame.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = GameViewModel(this)
        setContent {
            MaterialTheme {
                GameScreen(viewModel = viewModel)
            }
        }
    }
}
