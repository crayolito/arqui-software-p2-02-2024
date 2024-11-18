package com.parcial.apparquip1.Presentacion

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.CategoriaEjer
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.Negocio.NCategoriaEjer
import com.parcial.apparquip1.Negocio.NPlanEjercicio

class PPlanEjercicio(context: Context) {
    private val nPlanEjercicio: NPlanEjercicio = NPlanEjercicio(context)
    private val nCategoriaEjer: NCategoriaEjer = NCategoriaEjer(context)

    var id: Int = 0
    var idCategoriaEjercicio: Int = 0
    var titulo: String = ""
    var motivo: String = ""
    var objetivo: String = ""
    var video: String = ""
    var proceso: String = ""

    fun insertarPlanEjercicio(): String {
        return nPlanEjercicio.insertarPlanEjercicio(
            idCategoriaEjercicio, titulo, motivo, objetivo, video, proceso
        );
    }

    fun obtenerPlanesEjercicio(): List<PlanEjercicio> {
        return nPlanEjercicio.obtenerPlanesEjercicio()
    }

    fun actualizarPlanEjercicio(): String {
        return nPlanEjercicio.actualizarPlanEjercicio(
            idCategoriaEjercicio,id, titulo, motivo, objetivo, video, proceso
        );
    }

    fun eliminarPlanEjercicio(): String {
        return nPlanEjercicio.eliminarPlanEjercicio(id)
    }

    fun obtenerCategoriasEjer(): List<CategoriaEjer> {
        return nCategoriaEjer.obtenerCategoriasEjer();
    }

    fun getRelacionOfRutina(id: Int): List<Rutina> {
        return nPlanEjercicio.getRelacionOfRutina(id)
    }
}