package com.comercio.comercioapk.principal.productos


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseFragment
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.databinding.FragmentProductosBinding
import com.comercio.comercioapk.principal.productos.adapter.ProductosAdapter
import com.comercio.comercioapk.principal.productos.listener.ProductosListener
import com.comercio.comercioapk.principal.productos.model.ProductosUi
import com.comercio.comercioapk.util.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class ProductosFragment : BaseFragment<FragmentProductosBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProductosViewModel
    lateinit var productosAdapter: ProductosAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    private var listener: ProductosListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProductosListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    override fun getLayout(): Int {
        return R.layout.fragment_productos
    }

    override fun initViewModel() {
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun initObserver() {
        initAdapterProductos()
        viewModel.obtenerListaProductos.observe(viewLifecycleOwner, Observer { result ->
            obtenerListaProductos(result)
        })
        viewModel.clickAgregarCarrito.observe(viewLifecycleOwner, Observer { result ->
            initResultadoGuardarProducto(result)
        })
    }

    private fun initResultadoGuardarProducto(result: Resource<String>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    Timber.d("SUCCESS : %s ", it)
                    mostrarMensaje(result.data)
                    if (listener != null) listener!!.onClickAgregarCarrito()
                }!!
            }
            Status.LOADING -> Timber.d("LOADING")
            Status.ERROR -> Timber.d("ERROR: %s ", result.message)
        }
    }

    private fun mostrarMensaje(data: String) {
        Toast.makeText(activity, data, Toast.LENGTH_SHORT).show()
    }

    private fun initAdapterProductos() {
        productosAdapter = ProductosAdapter({
            Timber.d("guardarCarrito")
            initGuardarProductoCarrito(it)
        }, {
            //initStartActivityDetalles(it)
        })
        binding.recicladorProductos.adapter = productosAdapter
        setLayoutManager()
    }

    private fun setLayoutManager() {
        mLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recicladorProductos.layoutManager = mLayoutManager
        binding.recicladorProductos.setHasFixedSize(true)
        binding.recicladorProductos.adapter = productosAdapter
    }

    private fun obtenerListaProductos(result: Resource<List<ProductosUi>>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    productosAdapter.actualizarLista(result.data)
                    Timber.d("result.data.size : %s ", result.data.size)
                }!!
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("CONEXIÓN")
        }
    }

    private fun initGuardarProductoCarrito(it: ProductosUi) {
        viewModel.obteniendoProductosUi(it)
    }


}