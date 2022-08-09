package com.comercio.comercioapk.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comercio.comercioapk.model.Productos

@Dao
interface ProductosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarProductos(productos: List<Productos>)

    @Query("SELECT * FROM productos LIMIT 15 ")
    fun obtenerListaProductos(): LiveData<List<Productos>>

    @Query("SELECT * FROM productos")
    fun obtenerListaProductosAll(): LiveData<List<Productos>>
}