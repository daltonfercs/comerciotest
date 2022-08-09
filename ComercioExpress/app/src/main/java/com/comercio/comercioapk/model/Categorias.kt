package com.comercio.comercioapk.model

import androidx.room.Entity

@Entity(tableName = "categorias", primaryKeys = ["id"])
data class Categorias(
    val id: Long,
    val nombreCategoria: String,
    val imagenCategoria: String
)