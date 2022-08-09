package com.comercio.comercioapk.login.service


data class LoginResponse(
    val error: Boolean,
    val message: String,
    val listadoProducto: List<ProductosResp>,
    val listadoCategoria: List<CategoriaResp>
)

data class ProductosResp(
    val id: Long,
    val nombreProducto: String,
    val descripcionProducto: String,
    val imagenProducto: String,
    val categoriaId: String,
    val precioProducto: String
)

data class CategoriaResp(
    val id: Long,
    val nombreCategoria: String,
    val imagenCategoria: String
)

data class Usuario(
    val usuario: String,
    val clave: String
)