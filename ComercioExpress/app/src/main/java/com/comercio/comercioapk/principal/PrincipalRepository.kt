package com.comercio.comercioapk.principal


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.ResponseHandler
import com.comercio.comercioapk.db.ComercioDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrincipalRepository @Inject constructor(
    private val db: ComercioDB
) {


    fun obtenerConteoCarrito(): LiveData<Resource<String>> {
        val data = MediatorLiveData<Resource<String>>()
        data.value = Resource.loading(null)
        data.addSource(db.carritoDao().obtenerConteoCarrito()) {
            if (it != null) {
                data.value = Resource.success(it)
            }
        }
        return data
    }


}