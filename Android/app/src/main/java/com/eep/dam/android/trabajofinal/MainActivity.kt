package com.eep.dam.android.trabajofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eep.dam.android.trabajofinal.ui.theme.TrabajoFinalTheme
import com.eep.dam.android.trabajofinal.ui.theme.Model.Estudiante
import com.eep.dam.android.trabajofinal.ui.theme.Screens.PantallaAñadirEstudiantes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabajoFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Mostrar la pantalla de añadir estudiante en lugar de la pantalla de saludo
                    PantallaAñadirEstudiantes(
                        onEstudianteAdded = {},
                        onListarRegistrosClicked = {} // Proporcionar una función vacía como valor predeterminado
                    )
                }
            }
        }
    }
}