package com.eep.dam.android.estudiantesprueba.network

data class Estudiante(
    val id: Int? = null, // Hacer el ID opcional
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val dni: String
)


