package com.istea.anotador

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.istea.anotador.ui.theme.AnotadorTheme
import kotlin.collections.mutableListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPage(
    navController: NavController,
    listaDeTareas: MutableList<Tarea>
) {
    var texto by remember { mutableStateOf("") }
    var showNuevaTarea by remember { mutableStateOf(false) }
    var listaFiltrada: List<Tarea> by remember { mutableStateOf(listaDeTareas) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BarraSuperior() },
        floatingActionButton = {
            AddButton(
                onAction = {
                    showNuevaTarea = true
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Input(
                value = texto,
                onValueChange = {
                    texto = it
                    listaFiltrada = listaDeTareas.filter { tarea ->
                        tarea.titulo.uppercase().contains(texto.uppercase()) || tarea.descripcion.uppercase().contains(texto.uppercase())
                    }
                }
            )
            LazyColumn {
                items(listaFiltrada) { item ->
                    TareaRow(
                        tarea = item,
                        onAction = { navController.navigate("detalle/${it.id}") }
                    )
                }
            }
        }

        if (showNuevaTarea) {
            NuevaTareaDialog(
                onConfirm = { titulo, descripcion ->
                    if (titulo.isNotEmpty()) {
                        val tarea = Tarea(titulo = titulo, descripcion = descripcion)
                        listaDeTareas.add(tarea)
                        listaFiltrada = listaDeTareas.filter { tarea ->
                            tarea.titulo.uppercase().contains(texto.uppercase()) || tarea.descripcion.uppercase().contains(texto.uppercase())
                        }
                    }
                    showNuevaTarea = false
                },
                onDismiss = {
                    showNuevaTarea = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(){
    TopAppBar(
        title = { Text(text = "Lista de tareas") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun AddButton(onAction: () -> Unit){
    FloatingActionButton(
        onClick = {
            onAction()
        }
    ) {
        Text(
            text = "+",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun Input(value: String, onValueChange: (value: String) -> Unit){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 5.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
        }
    )
}

@Composable
fun TareaRow(tarea: Tarea, onAction: (tarea: Tarea)->Unit) {
    Card(
        onClick = {
            onAction(tarea)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = tarea.titulo
        )
    }
}

@Composable
fun NuevaTareaDialog(onDismiss: () -> Unit, onConfirm: (titulo: String, descripcion: String) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    AlertDialog(
        title = { Text("Agregar tarea") },
        text = {
            Column {
                OutlinedTextField(
                    label = { Text("Titulo") },
                    value = titulo,
                    onValueChange = { titulo = it })
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    label = { Text("Descripción") },
                    value = descripcion,
                    onValueChange = { descripcion = it })
            }
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(titulo, descripcion)
            }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ListaPagePreview() {
    AnotadorTheme {
        ListaPage(
            navController = rememberNavController(),
            listaDeTareas = mutableListOf<Tarea>()
        )
    }
}