package com.comercio.comercioapk.principal.productos

import androidx.lifecycle.*
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.principal.productos.model.ProductosUi
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ProductosViewModel  @Inject constructor(
    private val repository: ProductosRepository
) : ViewModel() {

    private val productosUi = MutableLiveData<ProductosUi>()

    fun obteniendoProductosUi(input: ProductosUi) {
        productosUi.value = input
    }

    var clickAgregarCarrito = productosUi.switchMap { productos ->
        liveData(Dispatchers.IO) {
            System.out.println(productos)
            emit(Resource.loading(null))
            emit(repository.agregarCarrito(productos))
        }
    }

    val obtenerListaProductos: LiveData<Resource<List<ProductosUi>>> = repository.obtenerListaProductos()
}