package com.comercio.comercioapk.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comercio.comercioapk.model.Carrito


@Dao
interface CarritoDao {

    @Query("SELECT count(*) FROM carrito")
    fun obtenerConteoCarrito(): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun guardarCarrito(matches: Carrito)

    @Query("SELECT * FROM carrito where codigoProducto=:codigoProducto")
    fun validarExisteProducto(codigoProducto: String): Int

    @Query("SELECT * FROM carrito where codigoProducto=:codigoProducto")
    fun obtenerdatosCarrito(codigoProducto: String): Carrito

    @Query("UPDATE carrito set cantidadProducto=:cantidadProducto where codigoProducto=:codigoProducto")
    fun actualizarCantidadProducto(codigoProducto: String?, cantidadProducto: Int): Int

    @Query("UPDATE carrito set precioTotalProducto=:precioTotal where codigoProducto=:codigoProducto")
    fun actualizarPrecioTotal(codigoProducto: String?, precioTotal: String?): Int

    @Query("SELECT * from carrito")
    fun obtenerListaCarrito(): LiveData<List<Carrito>>

    @Query("DELETE FROM carrito WHERE idCarrito = :idCarrito")
    fun eliminarCarrito(idCarrito: String): Int


    @Query("SELECT cantidadProducto,precioTotalProducto,codigoProducto,idCarrito,precioProducto FROM carrito where codigoProducto=:codigoProducto")
    fun obtenerDatosProductoSqlite(codigoProducto: String): CarritoResp.ObtenerCantidadYPrecioTotal

}