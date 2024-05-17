package com.eep.dam.android.estudiantesprueba

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    estudiantesViewModel: EstudiantesViewModel,
    onShowAllClicked: () -> Unit,
    onAddClicked: (String, String, String, String) -> Unit // Pasar los campos como parámetros
) {
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
            onClick = { onAddClicked(nombre, apellido, correo, dni) } // Pasar los campos como parámetros
        ) {
            Text("Añadir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onShowAllClicked // Llamamos a la función proporcionada al hacer clic en este botón
        ) {
            Text("Mostrar Todos")
        }
    }
}