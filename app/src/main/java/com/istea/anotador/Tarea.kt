package com.istea.anotador

import java.util.UUID

data class Tarea(
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val descripcion: String,
)