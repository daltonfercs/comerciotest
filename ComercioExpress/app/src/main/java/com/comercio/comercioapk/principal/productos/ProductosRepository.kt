package com.comercio.comercioapk.principal.productos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.db.ComercioDB
import com.comercio.comercioapk.model.Carrito
import com.comercio.comercioapk.model.Productos
import com.comercio.comercioapk.principal.productos.model.ProductosUi
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProductosRepository @Inject constructor(
    private val db: ComercioDB
) {

    fun obtenerListaProductos(): LiveData<Resource<List<ProductosUi>>> {
        val data = MediatorLiveData<Resource<List<ProductosUi>>>()
        data.value = Resource.loading(null)
        data.addSource(db.productosDao().obtenerListaProductosAll()) {
            if (it != null) {
                data.value = Resource.success(returListaProductosUi(it))
            }
        }
        return data
    }

    private fun returListaProductosUi(it: List<Productos>?): List<ProductosUi>? {
        val listProductosUi: MutableList<ProductosUi> = ArrayList()
        if (it != null) {
            for (productos in it) {
                listProductosUi.add(
                    ProductosUi(
                        productos.id,
                        productos.nombreProducto,
                        productos.descripcionProducto,
                        productos.imagenProducto,
                        productos.categoriaId,
                        productos.precioProducto
                    )
                )
            }
        }
        return listProductosUi
    }


    fun agregarCarrito(productos: ProductosUi): Resource<String> {
        try {
            val validarExisteProducto =
                db.carritoDao().validarExisteProducto(productos.id.toString())
            Timber.d("validarExisteProducto: %s", validarExisteProducto)
            if (validarExisteProducto == 0) {
                /*No existe un Producto - Agregamos*/
                db.carritoDao().guardarCarrito(
                    Carrito(
                        productos.id.toString(),
                        productos.nombreProducto,
                        1,
                        productos.imagenProducto,
                        productos.precioProducto,
                        productos.precioProducto
                    )
                )
                return Resource.success("Guardo Correctamente")
            } else {
                /*Actualizamos*/
                val carrito = db.carritoDao().obtenerdatosCarrito(productos.id.toString())
                Timber.d("carrito: %s ", carrito.nombreProducto)
                val totalCantidad = carrito.cantidadProducto.plus(1)
                Timber.d("totalCantidad: %s ", totalCantidad)

                val actualizarProducto = db.carritoDao().actualizarCantidadProducto(
                    productos.id.toString(),
                    totalCantidad
                )
                if (actualizarProducto == -1) {
                    return Resource.success("actualizarProducto Error")
                } else {

                    val carrito = db.carritoDao().obtenerdatosCarrito(productos.id.toString())

                    val cantidadProducto = carrito.cantidadProducto
                    val totalPrecioProducto = carrito.precioTotalProducto
                    val precioProducto = carrito.precioProducto

                    Timber.d("cantidadProducto: %s ", cantidadProducto)
                    Timber.d("totalPrecioProducto: %s ", totalPrecioProducto)
                    Timber.d("precioProducto: %s ", precioProducto)

                    val resultadoPreciototal = precioProducto.toDouble() * cantidadProducto
                    val decimal =
                        BigDecimal(resultadoPreciototal).setScale(2, RoundingMode.HALF_EVEN)
                    Timber.d("total: %s ", totalPrecioProducto)

                    val actualizarTotalProductos = db.carritoDao().actualizarPrecioTotal(
                        productos.id.toString(),
                        decimal.toString()
                    )


                    if (actualizarTotalProductos == -1) {
                        return Resource.success("actualizarTotalProductos Error")
                    } else {
                        return Resource.success("Actualizo Correctamente")
                    }

                }
            }
        } catch (e: Exception) {
            return Resource.error(e.localizedMessage, e.message)
        }
    }
}