package com.comercio.comercioapk.principal.categorias


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseFragment
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.databinding.FragmentCategoriasBinding
import com.comercio.comercioapk.principal.categorias.adapter.CategoriasAdapter
import com.comercio.comercioapk.util.injectViewModel
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import timber.log.Timber
import javax.inject.Inject


class CategoriasFragment : BaseFragment<FragmentCategoriasBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CategoriasViewModel
    lateinit var categoriaAdapter: CategoriasAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_categorias
    }

    override fun initViewModel() {
        viewModel = injectViewModel(viewModelFactory)

    }


    private fun initAdapter() {
        categoriaAdapter = CategoriasAdapter {
            //initStartActivityProdCat(it)
        }
        binding.recicladorCategorias.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recicladorCategorias.setItemAnimator(DefaultItemAnimator())
        binding.recicladorCategorias.adapter = categoriaAdapter
        binding.recicladorCategorias.setHasFixedSize(true)
    }

    override fun initObserver() {
        initAdapter()
        viewModel.obtenerListaCategorias.observe(viewLifecycleOwner, Observer { result ->
            initValidarCategoriaLista(result)
        })
    }

    private fun initValidarCategoriaLista(result: Resource<List<CategoriasUi>>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    categoriaAdapter.actualizarLista(result.data)
                }!!
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> {
                Timber.d("CONEXIÓN")
            }
        }
    }

}