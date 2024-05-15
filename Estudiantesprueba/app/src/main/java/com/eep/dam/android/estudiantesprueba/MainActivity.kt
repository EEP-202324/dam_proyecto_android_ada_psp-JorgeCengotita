package com.eep.dam.android.estudiantesprueba

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val estudiantesViewModel: EstudiantesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(estudiantesViewModel) {
                startActivity(Intent(this@MainActivity, MostrarTodosActivity::class.java))
            }
        }
    }
}
