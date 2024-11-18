package com.parcial.apparquip1.Negocio

import android.content.Context
import com.parcial.apparquip1.Datos.DPlanEjercicio
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina

class NPlanEjercicio(context: Context) : NPlanBase() {
    private val dPlanEjercicio: DPlanEjercicio = DPlanEjercicio(context)

    /**
     * Método público para insertar un plan de ejercicio
     */
    fun insertarPlanEjercicio(
        idCategoriaEjercicio: Int,
        titulo: String,
        motivo: String,
        objetivo: String,
        video: String,
        proceso: String
    ): String {
        val datos = mapOf(
            "idCategoria" to idCategoriaEjercicio.toString(),
            "titulo" to titulo,
            "motivo" to motivo,
            "objetivo" to objetivo,
            "video" to video,
            "proceso" to proceso
        )
        return ejecutarInsercion(datos)
    }

    /**
     * Método público para actualizar un plan de ejercicio
     */
    fun actualizarPlanEjercicio(
        id: Int,
        idCategoriaEjercicio: Int,
        titulo: String,
        motivo: String,
        objetivo: String,
        video: String,
        proceso: String
    ): String {
        val datos = mapOf(
            "idCategoria" to idCategoriaEjercicio.toString(),
            "titulo" to titulo,
            "motivo" to motivo,
            "objetivo" to objetivo,
            "video" to video,
            "proceso" to proceso
        )
        return ejecutarActualizacion(id, datos)
    }

    /**
     * Método público para eliminar un plan de ejercicio
     */
    fun eliminarPlanEjercicio(id: Int): String {
        return ejecutarEliminacion(id)
    }

    /**
     * Implementación de métodos abstractos
     */
    override fun validarDatos(datos: Map<String, String>): Boolean {
        return datos.values.all { it.isNotEmpty() } &&
                datos["idCategoria"]?.toIntOrNull() != null
    }

    override fun procesarDatos(datos: Map<String, String>): Boolean {
        try {
            dPlanEjercicio.apply {
                idCategoriaEjercicio = datos["idCategoria"]?.toInt() ?: 0
                titulo = datos["titulo"] ?: ""
                motivo = datos["motivo"] ?: ""
                objetivo = datos["objetivo"] ?: ""
                video = datos["video"] ?: ""
                proceso = datos["proceso"] ?: ""
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun guardarEnBD(): String {
        return dPlanEjercicio.insertarPlanEjercicio()
    }

    override fun procesarDatosActualizacion(id: Int, datos: Map<String, String>): Boolean {
        try {
            dPlanEjercicio.apply {
                this.id = id
                idCategoriaEjercicio = datos["idCategoria"]?.toInt() ?: 0
                titulo = datos["titulo"] ?: ""
                motivo = datos["motivo"] ?: ""
                objetivo = datos["objetivo"] ?: ""
                video = datos["video"] ?: ""
                proceso = datos["proceso"] ?: ""
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun actualizarEnBD(): String {
        return dPlanEjercicio.actualizarPlanEjercicio()
    }

    override fun eliminarEnBD(id: Int): String {
        dPlanEjercicio.id = id
        return dPlanEjercicio.eliminarPlanEjercicio()
    }

    /**
     * Métodos adicionales específicos
     */
    fun obtenerPlanesEjercicio(): List<PlanEjercicio> {
        return dPlanEjercicio.obtenerPlanesEjercicio()
    }

    fun getRelacionOfRutina(id: Int): List<Rutina> {
        dPlanEjercicio.id = id
        return dPlanEjercicio.getRelacionOfRutina()
    }
}