package com.comercio.comercioapk.principal.inicio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.comercio.comercioapk.R
import com.comercio.comercioapk.databinding.ItemCategoriaBinding
import pe.farmacias.peruanas.cajeroexpress.principal.categorias.model.CategoriasUi
import com.comercio.comercioapk.principal.inicio.adapter.holder.CategoriaHolder

class CategoriaAdapter(
    private val onClickCategoria: (CategoriasUi) -> Unit
) : RecyclerView.Adapter<CategoriaHolder>() {

    private var categoriaList = mutableListOf<CategoriasUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
        val binding = DataBindingUtil.inflate<ItemCategoriaBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_categoria, parent, false
        )
        return CategoriaHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoriaList.size
    }

    override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
        val categoria = categoriaList.get(position)
        holder.bind(categoria,onClickCategoria)
    }

    fun actualizarLista(categoriaList: List<CategoriasUi>) {
        this.categoriaList.clear()
        this.categoriaList.addAll(categoriaList)
        notifyDataSetChanged()
    }
}