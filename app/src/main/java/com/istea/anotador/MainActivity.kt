package com.istea.anotador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.istea.anotador.ui.theme.AnotadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotadorTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "lista") {
                    composable("lista") { ListaPage(navController) }
                    composable("detalle/{texto}") { backstackEntry ->
                        val texto = backstackEntry.arguments?.getString("texto") ?: ""
                        DetallePage(texto)
                    }
                }
            }
        }
    }
}