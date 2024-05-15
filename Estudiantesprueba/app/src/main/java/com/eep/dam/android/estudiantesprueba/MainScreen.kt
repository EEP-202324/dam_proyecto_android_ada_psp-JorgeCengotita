package com.eep.dam.android.estudiantesprueba

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(estudiantesViewModel: EstudiantesViewModel, onShowAllClicked: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") }
        )
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") }
        )
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") }
        )

        Button(
            onClick = {
                // Realizar la solicitud a la API
                estudiantesViewModel.obtenerEstudiantes()
            }
        ) {
            Text("Mostrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onShowAllClicked // Llamamos a la función proporcionada al hacer clic en este botón
        ) {
            Text("Mostrar Todos")
        }
    }
}


