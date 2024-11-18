package com.parcial.apparquip1.Negocio

import android.content.Context
import com.parcial.apparquip1.Datos.DRutina
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina

class NRutina(context: Context) {
    private val dRutina: DRutina = DRutina(context)

    fun obtenerRutina(id: Int): Rutina? {
        if (id <= 0) {
            return null
        }
        dRutina.id = id
        return dRutina.obtenerRutina()
    }

    fun insertarRutina(
        idPlanAlimentacion: Int, idPlanesEjercicios: List<PlanEjercicio>, rutina: Rutina
    ): String {
        dRutina.idPlanAlimentacion = idPlanAlimentacion
        dRutina.idPlanesEjercicios = idPlanesEjercicios
        dRutina.rutina = rutina
        return dRutina.insertarRutina()
    }

    fun obtenerRutinas(): List<Rutina> {
        return dRutina.obtenerRutinas()
    }

    fun actualizarRutina(idPlanAlimentacion: Int, idPlanesEjercicios: List<PlanEjercicio>, rutina: Rutina): String {
        if (idPlanAlimentacion <= 0) {
            return "El id del plan de alimentación no es válido"
        }
        dRutina.idPlanAlimentacion = idPlanAlimentacion
        dRutina.idPlanesEjercicios = idPlanesEjercicios
        dRutina.rutina = rutina
        return dRutina.actualizarRutina()
    }

    fun eliminarRutina(id: Int): String {
        if (id <= 0) {
            return "El id de la rutina no es válido"
        }
        dRutina.id = id
        return dRutina.eliminarRutina()
    }
}