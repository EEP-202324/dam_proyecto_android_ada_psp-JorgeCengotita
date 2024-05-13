package com.eep.dam.android.trabajofinal.ui.theme.Model

import kotlinx.serialization.Serializable
@Serializable
data class Estudiante(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val dni: String,
    val owner: String
)
