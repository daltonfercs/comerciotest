package com.comercio.comercioapk.model

import androidx.room.Entity

@Entity(tableName = "productos", primaryKeys = ["id"])
data class Productos(
    val id: Long,
    val nombreProducto: String,
    val descripcionProducto: String,
    val imagenProducto: String,
    val categoriaId: String,
    val precioProducto: String
)