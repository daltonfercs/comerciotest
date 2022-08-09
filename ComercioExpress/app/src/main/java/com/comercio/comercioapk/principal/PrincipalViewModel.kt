package com.comercio.comercioapk.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.comercio.comercioapk.base.Resource
import timber.log.Timber
import javax.inject.Inject

class PrincipalViewModel @Inject constructor(
    private val repository: PrincipalRepository
) : ViewModel() {

    val posicion = MutableLiveData<Int>()
    var posicionFragment: Int = 0

    val carritoConteo: LiveData<Resource<String>> = repository.obtenerConteoCarrito()



    fun initPosicionAdapter(it: Int) {
        this.posicionFragment = it
        when (it) {
            0 -> {
                Timber.d("posicionFragment : %s ", it)
                posicion.postValue(it)
            }
            1 -> {
                posicion.postValue(it)
                Timber.d("posicionFragment : %s ", it)
            }
            2 -> {
                posicion.postValue(it)
                Timber.d("posicionFragment : %s ", it)
            }
            3 -> {
                posicion.postValue(it)
                Timber.d("posicionFragment : %s ", it)
            }
            else -> Timber.d("Ocurrio algun problema")
        }
    }

}