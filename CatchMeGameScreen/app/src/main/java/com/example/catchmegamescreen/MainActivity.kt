//package com.example.catchmegamescreen
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import com.example.catchmegamescreen.catchMeGame.GameScreen
//import com.example.catchmegamescreen.catchMeGame.GameViewModel
//
//
//class MainActivity : ComponentActivity() {
//    private val username = "Usuario"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        val viewModel = GameViewModel(this)
//        setContent {
//                GameScreen(viewModel = viewModel)
//        }
//    }
//}

package com.example.catchmegamescreen.catchMeGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

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

