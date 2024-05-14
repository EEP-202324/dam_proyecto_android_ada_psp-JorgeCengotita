package com.eep.dam.android.trabajofinal.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eep.dam.android.trabajofinal.ui.theme.TrabajoFinalTheme
import com.eep.dam.android.trabajofinal.ui.theme.Model.Estudiante
import kotlinx.serialization.ExperimentalSerializationApi
import androidx.compose.foundation.border
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@ExperimentalSerializationApi
@Composable
fun PantallaAñadirEstudiantes(
    onEstudianteAdded: (Estudiante) -> Unit,
    onListarRegistrosClicked: () -> Unit // Nuevo parámetro para manejar el clic en el botón "Listar registros"
) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var owner by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título "Universidad CRUD"
        Text(
            text = "Universidad CRUD",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Campos de entrada para cada propiedad del estudiante
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp) // Agrega un padding horizontal de 8.dp
        )
        Spacer(modifier = Modifier.height(7.dp)) // Agrega un espacio de 8dp entre los campos de entrada
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp) // Agrega un padding horizontal de 8.dp
        )
        Spacer(modifier = Modifier.height(7.dp)) // Agrega un espacio de 8dp entre los campos de entrada
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp) // Agrega un padding horizontal de 8.dp
        )
        Spacer(modifier = Modifier.height(7.dp)) // Agrega un espacio de 8dp entre los campos de entrada
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp) // Agrega un padding horizontal de 8.dp
        )
        Spacer(modifier = Modifier.height(7.dp)) // Agrega un espacio de 8dp entre los campos de entrada
        OutlinedTextField(
            value = owner,
            onValueChange = { owner = it },
            label = { Text("Owner") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp) // Agrega un padding horizontal de 8.dp
        )
        Spacer(modifier = Modifier.height(7.dp)) // Agrega un    espacio de 8dp entre los campos de entrada
        Button(
            onClick = {
                val estudiante = Estudiante(
                    id = 0, // Puedes dejarlo en 0 si el ID se autogenera en la base de datos
                    nombre = nombre,
                    apellidos = apellidos,
                    correo = correo,
                    dni = dni,
                    owner = owner
                )
                onEstudianteAdded(estudiante)
                // Aquí podrías llamar a una función para insertar el estudiante en la base de datos
                // Por ejemplo, utilizando Room o cualquier otra solución de persistencia de datos
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Agrega un espacio de 16dp entre los botones
        Button(
            onClick = {
                onListarRegistrosClicked() // Llama a la función de "Listar registros"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Listar registros")
        }
    }
}

@ExperimentalSerializationApi
@Preview(showBackground = true)
@Composable
fun PreviewAddEstudianteScreen() {
    TrabajoFinalTheme {
        PantallaAñadirEstudiantes(
            onEstudianteAdded = {},
            onListarRegistrosClicked = {} // Pasamos una función vacía para la previsualización
        )
    }
}