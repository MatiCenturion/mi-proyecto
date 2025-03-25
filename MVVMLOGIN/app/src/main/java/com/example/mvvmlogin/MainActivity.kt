package com.example.mvvmlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmlogin.ui.theme.MVVMLOGINTheme
import com.example.mvvmlogin.ui.theme.login.menu.MenuScreen
import com.example.mvvmlogin.ui.theme.login.ui.LoginScreen
import com.example.mvvmlogin.ui.theme.login.ui.LoginViewModel
import com.example.mvvmlogin.ui.theme.login.catchMeGame.GameScreen
import com.example.mvvmlogin.ui.theme.login.catchMeGame.GameViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMLOGINTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            // Instancia del LoginViewModel
                            val loginViewModel = LoginViewModel()
                            LoginScreen(
                                viewModel = loginViewModel,
                                onLoginSuccess = {
                                    // Navegar al menú una vez completado el login
                                    navController.navigate("menu")
                                }
                            )
                        }
                        composable("menu") {
                            MenuScreen(
                                onGameSelected = {
                                    navController.navigate("game")
                                }
                            )
                        }
                        composable("game") {
                            val context = LocalContext.current // Obtiene el contexto de la composición
                            val gameViewModel = GameViewModel(context) // Pasa el contexto al ViewModel
                            GameScreen(viewModel = gameViewModel)
                        }
                        composable( "apiSolicitud") {
                            val context = LocalContext.current
                            val RickAndMortyViewModel = RickAndMortyViewModel(context)
                            RickAndMortyScreen(viewModel = RickAndMortyViewModel)
                        }
                    }
                }
            }
        }
    }
}
