package com.comercio.comercioapk.principal.productos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.comercio.comercioapk.R
import com.comercio.comercioapk.databinding.ItemProductosBinding
import com.comercio.comercioapk.principal.productos.adapter.holder.ProductosHolder
import com.comercio.comercioapk.principal.productos.model.ProductosUi

class ProductosAdapter(
    private val clickAgregarCarrito: (ProductosUi) -> Unit,
    private val clickMostrarDetalles: (ProductosUi) -> Unit
) : RecyclerView.Adapter<ProductosHolder>() {

    private var productosList = mutableListOf<ProductosUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHolder {
        val binding = DataBindingUtil.inflate<ItemProductosBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_productos, parent, false
        )
        return ProductosHolder(binding)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    override fun onBindViewHolder(holder: ProductosHolder, position: Int) {
        val productos = productosList.get(position)
        holder.bind(productos,clickAgregarCarrito,clickMostrarDetalles)
    }

    fun actualizarLista(productosList: List<ProductosUi>) {
        this.productosList.clear()
        this.productosList.addAll(productosList)
        notifyDataSetChanged()
    }
}