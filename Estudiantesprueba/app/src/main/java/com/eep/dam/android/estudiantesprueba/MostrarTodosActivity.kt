package com.eep.dam.android.estudiantesprueba

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                EstudianteCard(estudiante = estudiante)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun EstudianteCard(estudiante: Estudiante) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ID: ${estudiante.id}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
            )
            Text(
                text = "Nombre: ${estudiante.nombre}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
            )
            Text(
                text = "Apellido: ${estudiante.apellidos}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
            )
            Text(
                text = "Correo: ${estudiante.correo}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
            )
            Text(
                text = "DNI: ${estudiante.dni}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
            )
        }
    }
}

