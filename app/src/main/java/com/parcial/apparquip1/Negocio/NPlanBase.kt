package com.parcial.apparquip1.Negocio

abstract class NPlanBase {
    /**
     * Template method para inserción
     */
    protected fun ejecutarInsercion(datos: Map<String, String>): String {
        if (!validarDatos(datos)) {
            return "Faltan datos"
        }

        if (!procesarDatos(datos)) {
            return "Error al procesar datos"
        }

        return guardarEnBD()
    }

    /**
     * Template method para actualización
     */
    protected fun ejecutarActualizacion(id: Int, datos: Map<String, String>): String {
        if (id <= 0) {
            return "ID inválido"
        }

        if (!validarDatos(datos)) {
            return "Faltan datos"
        }

        if (!procesarDatosActualizacion(id, datos)) {
            return "Error al procesar datos"
        }

        return actualizarEnBD()
    }

    /**
     * Template method para eliminación
     */
    protected fun ejecutarEliminacion(id: Int): String {
        if (id <= 0) {
            return "ID inválido"
        }
        return eliminarEnBD(id)
    }

    // Métodos abstractos que deben implementar las clases hijas
    protected abstract fun validarDatos(datos: Map<String, String>): Boolean
    protected abstract fun procesarDatos(datos: Map<String, String>): Boolean
    protected abstract fun guardarEnBD(): String
    protected abstract fun procesarDatosActualizacion(id: Int, datos: Map<String, String>): Boolean
    protected abstract fun actualizarEnBD(): String
    protected abstract fun eliminarEnBD(id: Int): String
}