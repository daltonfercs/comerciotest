package com.comercio.comercioapk.principal.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.db.ComercioDB
import com.comercio.comercioapk.model.Categorias
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoriasRepository @Inject constructor(
    private val db: ComercioDB
) {


    fun obtenerListaCategorias(): LiveData<Resource<List<CategoriasUi>>> {
        val data = MediatorLiveData<Resource<List<CategoriasUi>>>()
        data.value = Resource.loading(null)
        data.addSource(db.categoriasDao().obtenerListaCategorias()) {
            if (it != null) {
                data.value = Resource.success(setListaCategoria(it))
            }
        }
        return data
    }

    private fun setListaCategoria(it: List<Categorias>): MutableList<CategoriasUi> {
        val listaCategorias: MutableList<CategoriasUi> = ArrayList()
        for (cat in it) {
            val categoriasUi = CategoriasUi(
                cat.id,
                cat.nombreCategoria,
                cat.imagenCategoria,
                "4"
            )
            listaCategorias.add(categoriasUi)
        }
        return listaCategorias
    }

}