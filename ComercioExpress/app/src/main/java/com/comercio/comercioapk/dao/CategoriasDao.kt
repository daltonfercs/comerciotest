package com.comercio.comercioapk.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comercio.comercioapk.model.Categorias
import com.comercio.comercioapk.model.Productos

@Dao
interface CategoriasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategorias(categoria:List<Categorias>)

    @Query("SELECT * from categorias")
    fun obtenerListaCategorias(): LiveData<List<Categorias>>
}