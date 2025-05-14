package com.example.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial1.ui.theme.Parcial1Theme
//Desarrollado por Wilfredo Cano y Allan Vega
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial1Theme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var nota by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color(0xFFADD8E6))
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = "Parcial #1",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Estudiante: Wilfredo Cano 8-1003-721", fontSize = 18.sp)
            Text(text = "Estudiante: Allan Vega 8-1001-2089", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Ingrese la nota a validar")

            OutlinedTextField(
                value = nota,
                onValueChange = { nota = it },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                resultado = validarNota(nota)
                showDialog = true
            }) {
                Text("Validar")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cerrar")
                        }
                    },
                    title = { Text("Resultado") },
                    text = { Text(resultado) }
                )
            }
        }
    }
}

fun validarNota(notaStr: String): String {
    val nota = notaStr.toIntOrNull() ?: return "Solo números"
    return when (nota) {
        in 91..100 -> "A (Excelente)"
        in 81..90 -> "B (Bueno)"
        in 71..80 -> "C (Regular)"
        in 61..70 -> "D (Más o menos regular)"
        in 0..60 -> "No Aprobado"
        else -> "Nota fuera de rango"
    }
}
