package com.eep.dam.android.estudiantesprueba

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.eep.dam.android.estudiantesprueba.network.ApiService
import com.eep.dam.android.estudiantesprueba.network.Estudiante
import com.eep.dam.android.estudiantesprueba.network.RetrofitClient
import okhttp3.Credentials

class EstudiantesViewModel : ViewModel() {

    private val username = "usuario1"
    private val password = "abc123"

    private val _estudiantes = MutableLiveData<List<Estudiante>>()
    val estudiantes: LiveData<List<Estudiante>> get() = _estudiantes

    fun obtenerEstudiantes() {
        val credentials = Credentials.basic(username, password)
        val retrofit = RetrofitClient.getClient("http://10.0.2.2:8080/")
        val apiService = retrofit.create(ApiService::class.java)

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.obtenerEstudiantes(credentials).execute().body() ?: emptyList()
                }
                _estudiantes.postValue(response)
            } catch (e: Exception) {
                Log.e("Estudiantes", "Error al obtener estudiantes", e)
            }
        }
    }
}


