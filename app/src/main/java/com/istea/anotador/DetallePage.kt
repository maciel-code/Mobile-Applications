package com.istea.anotador

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.istea.anotador.ui.theme.AnotadorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePage(texto: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Pantalla de detalle") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
    ){ innerPadding ->
        Column( modifier = Modifier.padding(innerPadding)) {
            Text(
                text = texto,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetallePagePreview() {
    AnotadorTheme {
        DetallePage("jojo")
    }
}