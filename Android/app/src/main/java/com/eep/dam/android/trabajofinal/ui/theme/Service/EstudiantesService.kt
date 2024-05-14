package com.eep.dam.android.trabajofinal.ui.theme.Service

import retrofit2.Call
import com.eep.dam.android.trabajofinal.ui.theme.Model.Estudiante
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EstudianteService {
    @GET("/estudiantes/{id}")
    fun getEstudiantesById(@Path("id") id: Int): Call<Estudiante>

    @POST("/estudiantes")
    fun createEstudiantes(@Body estudiantes: Estudiante): Call<Void>

    @GET("/estudiantes")
    fun getAllEstudiantes(): Call<List<Estudiante>>

    @PUT("/estudiantes/{id}")
    fun updateEstudiantes(@Path("id") id: Int, @Body estudiantes: Estudiante): Call<Void>

    @DELETE("/estudiantes/{id}")
    fun deleteEstudiantes(@Path("id") id: Int): Call<Void>


}

