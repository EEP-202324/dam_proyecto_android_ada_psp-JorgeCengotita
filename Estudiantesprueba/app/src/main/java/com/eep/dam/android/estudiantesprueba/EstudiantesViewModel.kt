package com.eep.dam.android.estudiantesprueba

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eep.dam.android.estudiantesprueba.network.ApiService
import com.eep.dam.android.estudiantesprueba.network.Estudiante
import com.eep.dam.android.estudiantesprueba.network.RetrofitClient
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EstudiantesViewModel : ViewModel() {
    private val username = "usuario1"
    private val password = "abc123"

    private val _estudiantes = MutableLiveData<List<Estudiante>>()
    val estudiantes: LiveData<List<Estudiante>> get() = _estudiantes

    fun obtenerEstudiantes() {
        val credentials = Credentials.basic(username, password)
        val retrofit = RetrofitClient.getClient("http://10.0.2.2:8080/")
        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.obtenerEstudiantes(credentials)
        call.enqueue(object : Callback<List<Estudiante>> {
            override fun onResponse(call: Call<List<Estudiante>>, response: Response<List<Estudiante>>) {
                if (response.isSuccessful) {
                    val estudiantes = response.body()
                    _estudiantes.postValue(estudiantes ?: emptyList())
                } else {
                    Log.e("Estudiantes", "Error al obtener estudiantes: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Estudiante>>, t: Throwable) {
                Log.e("Estudiantes", "Error de red al obtener estudiantes", t)
            }
        })
    }
}


