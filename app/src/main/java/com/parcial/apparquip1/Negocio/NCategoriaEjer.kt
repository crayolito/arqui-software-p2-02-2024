package com.parcial.apparquip1.Negocio

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.CategoriaEjer
import com.parcial.apparquip1.Datos.DCategoriaEjer
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio

class NCategoriaEjer(context: Context) {
    private val dCategoriaEjer: DCategoriaEjer = DCategoriaEjer(context)

    fun insertarCategoriaEjer(nombre: String, descripcion: String): String {
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            return "Faltan datos"
        }
        dCategoriaEjer.nombre = nombre
        dCategoriaEjer.descripcion = descripcion
        return dCategoriaEjer.insertarCategoriaEjer()
    }

    fun obtenerCategoriasEjer(): List<CategoriaEjer> {
        return dCategoriaEjer.obtenerCategoriasEjer()
    }

    fun actualizarCategoriaEjer(id: Int, nombre: String, descripcion: String): String {
        if (nombre == null || descripcion == null) {
            return "Categoria no encontrada"
        }
        dCategoriaEjer.id = id
        dCategoriaEjer.nombre = nombre
        dCategoriaEjer.descripcion = descripcion
        return dCategoriaEjer.actualizarCategoriaEjer()
    }

    fun eliminarCategoriaEjer(id: Int): String {
        if (id == null) {
            return "Categoria no encontrada"
        }
        return dCategoriaEjer.eliminarCategoriaEjer();
    }

    fun getRelacionOfCategoriaEjer(id: Int?): List<PlanEjercicio> {
        if (id == null) {
            return ArrayList<PlanEjercicio>()
        }
        dCategoriaEjer.id = id
        return dCategoriaEjer.getRelacionOfCategoriaEjer()
    }
}