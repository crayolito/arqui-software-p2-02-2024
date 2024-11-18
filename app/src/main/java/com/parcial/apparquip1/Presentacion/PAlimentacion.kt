package com.parcial.apparquip1.Presentacion

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.Negocio.NAlimentacion

class PAlimentacion(context: Context) {
    private val nAlimentacion: NAlimentacion = NAlimentacion(context)
    var id: Int = 0
    var titulo: String = ""
    var descripcion: String = ""
    var noprocesado: String = ""
    var procesado: String = ""

    fun insertarAlimento(): String {
        return nAlimentacion.insertarPlanAlimentacion(titulo, descripcion, noprocesado, procesado)
    }

    fun obtenerAlimentos(): List<Alimentacion> {
        return nAlimentacion.getPlanesAlimentacion()
    }

    fun actualizarAlimento(): String {
        return nAlimentacion.updatePlanAlimentacion(id, titulo, descripcion, noprocesado, procesado)
    }

    fun eliminarAlimento(): String {
        return nAlimentacion.deletePlanAlimentacion(id)
    }

    fun getRelacionOfRutina(): List<Rutina> {
        return nAlimentacion.getRelacionOfRutina(id)
    }
}