package com.eep.dam.android.estudiantesprueba.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("estudiantes")
    fun obtenerEstudiantes(@Header("Authorization") credentials: String): Call<List<Estudiante>>

    @POST("estudiantes")
    fun crearEstudiante(
        @Header("Authorization") credentials: String,
        @Body nuevoEstudiante: Estudiante
    ): Call<Estudiante>
}
