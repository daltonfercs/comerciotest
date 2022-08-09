package com.comercio.comercioapk.principal.productos.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comercio.comercioapk.databinding.ItemProductosBinding
import com.comercio.comercioapk.principal.productos.model.ProductosUi

class ProductosHolder(val binding: ItemProductosBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        productos: ProductosUi, clickAgregarCarrito: (ProductosUi) -> Unit,
        clickMostrarDetalles: (ProductosUi) -> Unit
    ) {
        binding.discount.text =  "10 %"
        binding.textViewPrecioNormal.text = "S/." + productos.precioProducto
        Glide.with(itemView.context).load(productos.imagenProducto).into(binding.imageView)
        binding.textViewPrecioTachado.text = "as"
        /*binding.textViewPrecioTachado.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }*/
        binding.textViewNombre.text = productos.nombreProducto
        with(binding.root) {
            setOnClickListener {
                clickMostrarDetalles(productos)
            }
        }
        with(binding.btnAgregarCarrito) {
            setOnClickListener {
                clickAgregarCarrito(productos)
            }
        }
    }
}