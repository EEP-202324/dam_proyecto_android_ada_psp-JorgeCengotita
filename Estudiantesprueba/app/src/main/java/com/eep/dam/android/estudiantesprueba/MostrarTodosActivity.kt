package com.eep.dam.android.estudiantesprueba

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eep.dam.android.estudiantesprueba.network.Estudiante

class MostrarTodosActivity : AppCompatActivity() {

    private val estudiantesViewModel: EstudiantesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MostrarTodosScreen(estudiantesViewModel = estudiantesViewModel)
        }

        estudiantesViewModel.obtenerEstudiantes()
    }
}

@Composable
fun MostrarTodosScreen(estudiantesViewModel: EstudiantesViewModel) {
    val estudiantes by estudiantesViewModel.estudiantes.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(estudiantes) { estudiante ->
                Text(text = "ID: ${estudiante.id}, Nombre: ${estudiante.nombre}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
