package com.comercio.comercioapk.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.comercio.comercioapk.dao.CarritoDao
import com.comercio.comercioapk.dao.CategoriasDao
import com.comercio.comercioapk.dao.ProductosDao
import com.comercio.comercioapk.model.Carrito
import com.comercio.comercioapk.model.Categorias
import com.comercio.comercioapk.model.Productos


@Database(
    entities = [Productos::class, Categorias::class, Carrito::class],
    version = 1,
    exportSchema = false
)
abstract class ComercioDB : RoomDatabase() {

    abstract fun productosDao(): ProductosDao

    abstract fun categoriasDao(): CategoriasDao

    abstract fun carritoDao(): CarritoDao

    companion object {

        @Volatile
        private var INSTANCE: ComercioDB? = null

        fun getInstance(context: Context): ComercioDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ComercioDB::class.java,
                    "ComercioDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}