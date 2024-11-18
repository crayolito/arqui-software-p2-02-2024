package com.parcial.apparquip1.Presentacion

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.CategoriaEjer
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Negocio.NCategoriaEjer

class PCategoriEjer(context: Context) {
    private val nCategoriEjer: NCategoriaEjer = NCategoriaEjer(context)
    var id: Int = 0
    var nombre: String = ""
    var descripcion: String = ""

    fun insertarCategoriaEjer(): String {
        return nCategoriEjer.insertarCategoriaEjer(nombre, descripcion)
    }

    fun obtenerCategoriasEjer(): List<CategoriaEjer> {
        return nCategoriEjer.obtenerCategoriasEjer()
    }

    fun actualizarCategoriaEjer(): String {
        return nCategoriEjer.actualizarCategoriaEjer(id, nombre, descripcion)
    }

    fun eliminarCategoriaEjer(): String {
        return nCategoriEjer.eliminarCategoriaEjer(id)
    }

    fun getRelacionOfCategoriaEjer(): List<PlanEjercicio> {
        return nCategoriEjer.getRelacionOfCategoriaEjer(id)
    }
}