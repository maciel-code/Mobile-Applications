package com.billeteravirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billeteravirtual.UI.BilleteraVirtualTheme

class ComprobanteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val monto = intent.getDoubleExtra("MONTO_RETIRADO", 0.0)

        setContent {
            BilleteraVirtualTheme {
                PantallaComprobante(monto)
            }
        }
    }
}

@Composable
fun PantallaComprobante(monto: Double) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Retiro exitoso 💸",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Monto retirado: $${String.format("%.2f", monto)}",
                fontSize = 22.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
