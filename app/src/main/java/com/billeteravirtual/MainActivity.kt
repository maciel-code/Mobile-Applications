package com.billeteravirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.billeteravirtual.UI.BilleteraVirtualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BilleteraVirtualTheme {
                BilleteraVirtualApp()
            }
        }
    }
}

@Composable
fun BilleteraVirtualApp() {
    var saldo by remember { mutableStateOf(10000.0) }
    var monto by remember { mutableStateOf("") }
    var mostrarComprobante by remember { mutableStateOf(false) }
    var montoRetirado by remember { mutableStateOf(0.0) }

    if (mostrarComprobante) {
        ComprobanteScreen(montoRetirado) {
            mostrarComprobante = false
        }
    } else {
        MainScreen(
            saldo = saldo,
            monto = monto,
            onMontoChange = { monto = it },
            onRetirar = {
                val retiro = monto.toDoubleOrNull() ?: 0.0
                if (retiro in 0.1..saldo) {
                    saldo -= retiro
                    montoRetirado = retiro
                    mostrarComprobante = true
                }
            }
        )
    }
}

@Composable
fun MainScreen(saldo: Double, monto: String, onMontoChange: (String) -> Unit, onRetirar: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Saldo actual: $${saldo}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = monto,
            onValueChange = onMontoChange,
            label = { Text("Monto a retirar") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetirar) {
            Text("Retirar")
        }
    }
}

@Composable
fun ComprobanteScreen(monto: Double, onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Retiro exitoso", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Monto retirado: $${monto}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}
