package com.eep.dam.android.trabajofinal

import com.eep.dam.android.trabajofinal.ui.theme.Service.EstudianteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val apiService: EstudianteService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EstudianteService::class.java)
    }
}
