package com.eep.dam.android.estudiantesprueba

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eep.dam.android.estudiantesprueba.network.Estudiante
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarTodosActivity : AppCompatActivity() {

    private val estudiantesViewModel: EstudiantesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        estudiantesViewModel.obtenerEstudiantes()
    }
}

