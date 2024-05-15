package com.eep.dam.android.estudiantesprueba.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("estudiantes")
    fun obtenerEstudiantes(@Header("Authorization") credentials: String): Call<List<Estudiante>>
}
