package com.comercio.comercioapk.login

import androidx.lifecycle.*
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.login.service.CategoriaResp
import com.comercio.comercioapk.login.service.ProductosResp
import com.comercio.comercioapk.login.service.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    val usuarioLiveData = MutableLiveData<Usuario>()

    fun initGuardarDB(
        listadoProducto: List<ProductosResp>, listadoCategoria: List<CategoriaResp>
    ) {
        viewModelScope.launch {
            repository.initGuardarDataProductos(
                listadoProducto,
                listadoCategoria
            )
        }
    }

    fun obteniendoUsuario(usuario: Usuario) {
        usuarioLiveData.value = usuario
    }

    var obtenerListaProductos = usuarioLiveData.switchMap { usuario ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.obtenerListaProductos(usuario))
        }
    }

}