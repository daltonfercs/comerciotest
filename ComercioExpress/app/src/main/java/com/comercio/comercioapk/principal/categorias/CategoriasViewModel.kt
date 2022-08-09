package com.comercio.comercioapk.principal.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.comercio.comercioapk.base.Resource
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import javax.inject.Inject

class CategoriasViewModel @Inject constructor(
    val repository: CategoriasRepository
) : ViewModel() {

    val obtenerListaCategorias: LiveData<Resource<List<CategoriasUi>>> =
        repository.obtenerListaCategorias()

}