package com.comercio.comercioapk.carrito

import androidx.lifecycle.*
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.model.Carrito
import kotlinx.coroutines.Dispatchers
import java.math.BigDecimal
import javax.inject.Inject

class CarritoViewModel @Inject constructor(
    private val repository: CarritoRepository
) : ViewModel() {

    val carritoLista: LiveData<Resource<List<Carrito>>> = repository.obtenerListaCarrito()
    val mostrarMensaje = MutableLiveData<String>()
    val carritoUi = MutableLiveData<Carrito>()
    private var responseResultadoSumar = MutableLiveData<String>()

    fun setCarrito(input: Carrito) {
        carritoUi.value = input
    }

    var eliminarCarrito = carritoUi.switchMap { carritoUi ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.eliminarProductoCarrito(carritoUi))
        }
    }

    fun sumarCantidadProducto(it: Carrito) {

        val cantidadEditar: Int? = it.cantidadProducto
        val totalCantidad = cantidadEditar?.plus(1)
        try {
            repository.agregarSumaProducto(it, totalCantidad!!, responseResultadoSumar)
        } catch (exeption: Exception) {
            mostrarMensaje.postValue(exeption.toString())
        }
    }

    fun restarCantidadProducto(it: Carrito) {
        val cantidadEditar: Int? = it.cantidadProducto
        val totalCantidad = cantidadEditar?.minus(1)
        if (totalCantidad == 0) {
            mostrarMensaje.postValue("No se permite valores nulos")
            return
        }
        try {
            repository.restarProducto(it, totalCantidad!!, responseResultadoSumar)
        } catch (exception: Exception) {
            mostrarMensaje.postValue(exception.toString())
        }
    }




}