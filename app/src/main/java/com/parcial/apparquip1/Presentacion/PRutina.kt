package com.parcial.apparquip1.Presentacion

import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.Negocio.NAlimentacion
import com.parcial.apparquip1.Negocio.NPlanEjercicio
import com.parcial.apparquip1.Negocio.NRutina
import android.content.Context

class PRutina(context: Context) {
    private val nRutina: NRutina = NRutina(context)
    private val nPlanEjercicio: NPlanEjercicio = NPlanEjercicio(context)
    private val nAlimentacion: NAlimentacion = NAlimentacion(context)

    var id: Int = 0
    var titulo: String = ""
    var idPlanAlimentacion: Int = 0
    var idPlanesEjercicios  : List<PlanEjercicio> = listOf()
    var rutina: Rutina = Rutina(0, "", 0)

    fun insertarRutina(): String {
        return nRutina.insertarRutina(idPlanAlimentacion, idPlanesEjercicios, rutina)
    }

    fun obtenerRutinas(): List<Rutina> {
        return nRutina.obtenerRutinas()
    }

    fun actualizarRutina(): String {
        return nRutina.actualizarRutina(idPlanAlimentacion, idPlanesEjercicios, rutina)
    }

    fun eliminarRutina(): String {
        return nRutina.eliminarRutina(id)
    }

    fun getPlanesEjercicios(): List<PlanEjercicio> {
        return nPlanEjercicio.obtenerPlanesEjercicio()
    }

    fun getPlanesAlimentacion(): List<Alimentacion> {
        return nAlimentacion.getPlanesAlimentacion()
    }
}