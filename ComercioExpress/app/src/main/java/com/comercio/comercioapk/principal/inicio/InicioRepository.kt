package com.comercio.comercioapk.principal.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.db.ComercioDB
import com.comercio.comercioapk.model.Categorias
import com.comercio.comercioapk.model.Productos
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import pe.farmacias.peruanas.cajeroexpress.principal.inicio.model.TopProducto
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class InicioRepository @Inject constructor(
    private val db: ComercioDB
) {

    fun obtenerListaTopProducto(): LiveData<Resource<List<TopProducto>>> {
        val data = MediatorLiveData<Resource<List<TopProducto>>>()
        data.value = Resource.loading(null)
        data.addSource(db.productosDao().obtenerListaProductos()) {
            if (it != null) {
                data.value = Resource.success(setListaProducto(it))
            }
        }
        return data
    }

    private fun setListaProducto(it: List<Productos>?): List<TopProducto>? {
        val listaProductos: MutableList<TopProducto> = ArrayList()
        if (it != null) {
            for (prod in it) {
                val productosUi = TopProducto(
                    prod.id,
                    prod.nombreProducto,
                    prod.descripcionProducto,
                    prod.imagenProducto,
                    prod.categoriaId,
                    prod.precioProducto
                )
                listaProductos.add(productosUi)
            }
        }
        return listaProductos
    }


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
                "-"
            )
            listaCategorias.add(categoriasUi)
        }
        return listaCategorias
    }
}