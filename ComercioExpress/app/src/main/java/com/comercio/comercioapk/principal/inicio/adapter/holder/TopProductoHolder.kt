package com.comercio.comercioapk.principal.inicio.adapter.holder


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comercio.comercioapk.databinding.ItemTopProductosBinding
import pe.farmacias.peruanas.cajeroexpress.principal.inicio.model.TopProducto
import timber.log.Timber

class TopProductoHolder(val binding: ItemTopProductosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: TopProducto, onClickHome: (TopProducto) -> Unit) {
        binding.textViewPrecioNormal.text = "S/." + producto.precioProducto
        binding.discount.text =  "10 %"
        Timber.d("imagenProductos : %s ",producto.imagenProducto)
        Glide.with(itemView.context).load(producto.imagenProducto).into(binding.imageView)
        //binding.textViewPrecioTachado.text = producto.preciotachado
        binding.textViewNombre.text = producto.nombreProducto

        /*
        binding.textViewPrecioTachado.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }*/



        with(itemView.rootView) {
            setOnClickListener {
                onClickHome(producto)
            }
        }
    }

}