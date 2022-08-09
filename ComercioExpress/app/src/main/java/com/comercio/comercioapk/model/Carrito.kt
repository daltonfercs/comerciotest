package com.comercio.comercioapk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito")
data class Carrito (
    var codigoProducto: String,
    var nombreProducto: String,
    var cantidadProducto: Int,
    var imagenProducto: String,
    var precioProducto: String,
    var precioTotalProducto: String
) {
    @PrimaryKey(autoGenerate = true)
    var idCarrito: Long? = null
    var codigoConteo: String? = null

}