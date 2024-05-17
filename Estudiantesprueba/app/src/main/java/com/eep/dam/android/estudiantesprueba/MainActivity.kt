package com.eep.dam.android.estudiantesprueba

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eep.dam.android.estudiantesprueba.network.Estudiante


class MainActivity : AppCompatActivity() {

    private val estudiantesViewModel: EstudiantesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                estudiantesViewModel,
                onShowAllClicked = { startActivity(Intent(this@MainActivity, MostrarTodosActivity::class.java)) },
                onAddClicked = { nombre, apellido, correo, dni ->
                    // Lógica para manejar el clic en el botón "Añadir"
                    val nuevoEstudiante = Estudiante(
                        nombre = nombre,
                        apellidos = apellido, // Cambiar a "apellidos"
                        correo = correo,
                        dni = dni
                    )
                    estudiantesViewModel.crearEstudiante(nuevoEstudiante)
                }
            )
        }
    }
}


