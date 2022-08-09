package com.comercio.comercioapk.login

import androidx.lifecycle.MutableLiveData
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.ResponseHandler
import com.comercio.comercioapk.db.ComercioDB
import com.comercio.comercioapk.login.service.*
import com.comercio.comercioapk.model.Categorias
import com.comercio.comercioapk.model.Productos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val service: LoginService,
    private val responseHandler: ResponseHandler,
    private val db: ComercioDB
) {

/*
    suspend fun obtenerListaProductos(): Resource<LoginResponse> {
        return try {
            val response = service.initLogin()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }*/

    suspend fun obtenerListaProductos(usuario: Usuario): Resource<LoginResponse> {
        return try {
            val response = service.initLogin(usuario.usuario, usuario.clave)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }


    suspend fun initGuardarDataProductos(
        listadoProductoBack: List<ProductosResp>,
        listadoCategoriaBack: List<CategoriaResp>
    ) {
        withContext(Dispatchers.IO) {
            val listaProductos: MutableList<Productos> = ArrayList()
            for (producto in listadoProductoBack) {
                listaProductos.add(
                    Productos(
                        producto.id,
                        producto.nombreProducto,
                        producto.descripcionProducto,
                        producto.imagenProducto,
                        producto.categoriaId,
                        producto.precioProducto
                    )
                )
            }
            db.productosDao().insertarProductos(listaProductos)

            withContext(Dispatchers.IO) {
                val listaCategorias: MutableList<Categorias> = ArrayList()
                for (categoria in listadoCategoriaBack) {
                    listaCategorias.add(
                        Categorias(
                            categoria.id,
                            categoria.nombreCategoria,
                            categoria.imagenCategoria
                        )
                    )
                }
                db.categoriasDao().insertCategorias(listaCategorias)
            }
        }
    }

    suspend fun initGuardarDataCategorias(
        listadoCategoriaBack: List<CategoriaResp>
    ) {
        withContext(Dispatchers.IO) {
            val listaCategorias: MutableList<Categorias> = ArrayList()
            for (categoria in listadoCategoriaBack) {
                listaCategorias.add(
                    Categorias(
                        categoria.id,
                        categoria.nombreCategoria,
                        categoria.imagenCategoria
                    )
                )
            }
            db.categoriasDao().insertCategorias(listaCategorias)
        }
    }


}