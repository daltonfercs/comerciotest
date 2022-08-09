package com.comercio.comercioapk.principal.inicio

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseFragment
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.databinding.FragmentInicioBinding
import com.comercio.comercioapk.principal.inicio.adapter.CategoriaAdapter
import com.comercio.comercioapk.principal.inicio.adapter.TopProductosAdapter
import com.comercio.comercioapk.util.injectViewModel
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import pe.farmacias.peruanas.cajeroexpress.principal.inicio.model.TopProducto
import timber.log.Timber
import javax.inject.Inject


class InicioFragment : BaseFragment<FragmentInicioBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: InicioViewModel

    lateinit var categoriaAdapter: CategoriaAdapter
    lateinit var productoAdapter: TopProductosAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_inicio
    }

    override fun initViewModel() {
        viewModel = injectViewModel(viewModelFactory)

    }


    private fun initAdapterCategoria() {
        binding.recicladorCategorias.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriaAdapter = CategoriaAdapter {
            //initStartActivityProdCat(it)
        }
        binding.recicladorCategorias.adapter = categoriaAdapter
        binding.recicladorPopulares.setHasFixedSize(true)
    }

    private fun initAdapterProducto() {
        binding.recicladorPopulares.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        productoAdapter = TopProductosAdapter {
           // initDetallesActivity(it)
        }
        binding.recicladorPopulares.adapter = productoAdapter
        binding.recicladorPopulares.setHasFixedSize(true)
        Timber.d("init Adapter")
    }

    override fun initObserver() {
        initAdapterProducto()
        initAdapterCategoria()
        viewModel.obtenerListaTopProductos.observe(viewLifecycleOwner, Observer { result ->
            obtenerListaTopProductos(result)
        })

        viewModel.obtenerListaCategorias.observe(viewLifecycleOwner, Observer { result ->
            obtenerListaCategorias(result)
        })

    }

    private fun obtenerListaCategorias(result: Resource<List<CategoriasUi>>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    categoriaAdapter.actualizarLista(it)
                }!!
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("ERROR CONEXIÓN")
        }
    }



    private fun obtenerListaTopProductos(result: Resource<List<TopProducto>>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    productoAdapter.actualizarLista(result.data)
                }!!
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("ERROR CONEXIÓN")
        }
    }


}