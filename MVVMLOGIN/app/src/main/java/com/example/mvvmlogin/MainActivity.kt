//package com.example.mvvmlogin
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import com.example.mvvmlogin.ui.theme.MVVMLOGINTheme
//import com.example.mvvmlogin.ui.theme.login.ui.LoginScreen
//import com.example.mvvmlogin.ui.theme.login.ui.LoginViewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MVVMLOGINTheme {
//                LoginScreen(LoginViewModel())
//            }
//        }
//    }
//}

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
import com.example.catchmegamescreen.catchMeGame.GameScreen
import com.example.mvvmlogin.ui.theme.MVVMLOGINTheme
import com.example.mvvmlogin.ui.theme.login.menu.MenuScreen
import com.example.mvvmlogin.ui.theme.login.ui.LoginScreen
import com.example.mvvmlogin.ui.theme.login.ui.LoginViewModel

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
                            // Obtenemos el contexto desde la composición
                            val context = LocalContext.current
                            // Instanciamos el GameViewModel pasándole el contexto
                            val gameViewModel = com.example.catchmegamescreen.catchMeGame.GameViewModel(context)
                            GameScreen(viewModel = gameViewModel)
                        }
                    }
                }
            }
        }
    }
}
