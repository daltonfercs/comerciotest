package com.comercio.comercioapk.carrito

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseActivity
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.carrito.adapter.CarritoAdapter
import com.comercio.comercioapk.databinding.ActivityCarritoBinding
import com.comercio.comercioapk.databinding.ActivityPrincipalBinding
import com.comercio.comercioapk.di.viewmodel.ViewModelProviderFactory
import com.comercio.comercioapk.model.Carrito
import com.comercio.comercioapk.util.injectViewModel
import kotlinx.android.synthetic.main.toolbar_carrito.view.*
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class CarritoActivity : BaseActivity<ActivityCarritoBinding>()  {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: CarritoViewModel
    lateinit var carritoAdapter: CarritoAdapter

    override fun getLayout(): Int {
        return R.layout.activity_carrito
    }

    override fun initViewModel() {
        viewModel = injectViewModel(providerFactory)
    }

    override fun initObserver() {
        initAdapter()
        viewModel.carritoLista.observe(this, Observer { result ->
            obtenerListaCarrito(result)
        })
        viewModel.eliminarCarrito.observe(this, Observer { result ->
            initEliminarCarrito(result)
        })
        viewModel.mostrarMensaje.observe(this, Observer { resultado ->
            initMostrarMensaje(resultado)
        })
    }
    private fun initMostrarMensaje(resultado: String?) {
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
    }
    private fun initEliminarCarrito(result: Resource<String>?) {
        when (result?.status) {
            Status.SUCCESS -> Timber.d("ELIMINO CORRECTAMENTE")
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("CONEXIÓN")
        }
    }
    private fun obtenerListaCarrito(result: Resource<List<Carrito>>) {
        when (result.status) {
            Status.SUCCESS -> {
                result.data?.let {
                    actualizarTotalProductos(it)
                    carritoAdapter.actualizarLista(it)
                }
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("ERROR TRAER DB")

        }
    }
    private fun actualizarTotalProductos(it: List<Carrito>) {
        var totalProductoLista = 0.0
        for (lista in it) {
            Timber.d("precioTotalProducto : %s ", lista.precioTotalProducto)
            totalProductoLista += lista.precioTotalProducto.toDouble()
        }
        val decimal = BigDecimal(totalProductoLista).setScale(2, RoundingMode.HALF_EVEN)
       // viewModel.obtenerTotalPrecio(decimal)
        binding.includeProductos.textViewNumero.text = it.size.toString()
        binding.textViewResultadoTotal.setText("S./" + decimal)
    }
    private fun initAdapter() {
        carritoAdapter = CarritoAdapter(
            {
                /*OnClick Button Sumar Carrito*/
                viewModel.sumarCantidadProducto(it)
            },
            {
                /*OnClick Button Restar Carrito*/
                viewModel.restarCantidadProducto(it)
            },
            {
                /*OnClick Button Eliminar Carrito*/
                viewModel.setCarrito(it)
            })
        binding.recicladorCarrito.layoutManager = LinearLayoutManager(this)
        binding.recicladorCarrito.adapter = carritoAdapter
        binding.recicladorCarrito.setHasFixedSize(true)
    }

}