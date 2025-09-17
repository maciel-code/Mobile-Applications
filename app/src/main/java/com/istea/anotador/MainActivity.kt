package com.istea.anotador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
                val listaDeTareas = rememberSaveable { mutableStateListOf<Tarea>() }

                NavHost(navController, startDestination = "lista") {
                    composable("lista") {
                        ListaPage(navController, listaDeTareas)
                    }
                    composable("detalle/{idTarea}") { backstackEntry ->
                        val idTarea = backstackEntry.arguments?.getString("idTarea") ?: ""
                        val tarea = listaDeTareas.find { it.id == idTarea } ?: Tarea(titulo = "", descripcion = "")
                        DetallePage(tarea)
                    }
                }
            }
        }
    }
}