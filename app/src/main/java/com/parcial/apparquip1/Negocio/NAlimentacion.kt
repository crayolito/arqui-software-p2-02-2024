package com.parcial.apparquip1.Negocio

import android.content.Context
import com.parcial.apparquip1.Datos.DAlimentos
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.Rutina

class NAlimentacion(context: Context) : NPlanBase() {
    private val dAlimentos: DAlimentos = DAlimentos(context)

    /**
     * Método público para insertar un plan de alimentación
     */
    fun insertarPlanAlimentacion(
        titulo: String,
        descripcion: String,
        noprocesado: String,
        procesado: String
    ): String {
        val datos = mapOf(
            "titulo" to titulo,
            "descripcion" to descripcion,
            "noprocesado" to noprocesado,
            "procesado" to procesado
        )
        return ejecutarInsercion(datos)
    }

    /**
     * Método público para actualizar un plan de alimentación
     */
    fun updatePlanAlimentacion(
        id: Int,
        titulo: String,
        descripcion: String,
        noprocesado: String,
        procesado: String
    ): String {
        val datos = mapOf(
            "titulo" to titulo,
            "descripcion" to descripcion,
            "noprocesado" to noprocesado,
            "procesado" to procesado
        )
        return ejecutarActualizacion(id, datos)
    }

    /**
     * Método público para eliminar un plan de alimentación
     */
    fun deletePlanAlimentacion(id: Int): String {
        return ejecutarEliminacion(id)
    }

    /**
     * Implementación de métodos abstractos
     */
    override fun validarDatos(datos: Map<String, String>): Boolean {
        return datos.values.all { it.isNotEmpty() }
    }

    override fun procesarDatos(datos: Map<String, String>): Boolean {
        try {
            dAlimentos.apply {
                titulo = datos["titulo"] ?: ""
                descripcion = datos["descripcion"] ?: ""
                noprocesado = datos["noprocesado"] ?: ""
                procesado = datos["procesado"] ?: ""
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun guardarEnBD(): String {
        return dAlimentos.insertarAlimento()
    }

    override fun procesarDatosActualizacion(id: Int, datos: Map<String, String>): Boolean {
        try {
            dAlimentos.apply {
                this.id = id
                titulo = datos["titulo"] ?: ""
                descripcion = datos["descripcion"] ?: ""
                noprocesado = datos["noprocesado"] ?: ""
                procesado = datos["procesado"] ?: ""
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun actualizarEnBD(): String {
        return dAlimentos.actualizarAlimento()
    }

    override fun eliminarEnBD(id: Int): String {
        dAlimentos.id = id
        return dAlimentos.eliminarAlimento()
    }

    /**
     * Métodos adicionales específicos
     */
    fun getPlanesAlimentacion(): List<Alimentacion> {
        return dAlimentos.obtenerAlimentos()
    }

    fun getRelacionOfRutina(id: Int): List<Rutina> {
        if (id <= 0) {
            return emptyList()
        }
        dAlimentos.id = id
        return dAlimentos.getRelacionOfRutina()
    }
}