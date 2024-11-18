package com.parcial.apparquip1.Negocio

import NMemento
import com.parcial.apparquip1.Screen.Memento


/**
 * Clase NCaretaker (Guardián)
 *
 * Esta clase es responsable de mantener el historial de estados (Mementos) y
 * gestionar las operaciones de deshacer/rehacer.
 *
 * El Caretaker nunca modifica los mementos, solo los almacena y gestiona.
 */
class NCaretaker {
    // Lista que almacena todos los estados guardados
    private val historial = mutableListOf<NMemento>()

    // Índice que indica la posición actual en el historial
    // -1 indica que no hay estados guardados
    private var indiceActual = -1

    /**
     * Agrega un nuevo estado al historial.
     * Si estamos en medio del historial, elimina todos los estados posteriores
     * para mantener una línea temporal coherente.
     *
     * @param recuerdo El memento (estado) que se va a guardar
     */
    fun agregarRecuerdo(recuerdo: NMemento) {
        // Si estamos en medio del historial, eliminamos los estados futuros
        if (indiceActual < historial.size - 1) {
            historial.subList(indiceActual + 1, historial.size).clear()
        }

        // Agregamos el nuevo estado y actualizamos el índice
        historial.add(recuerdo)
        indiceActual = historial.size - 1

        // Log para debugging
        println("Nuevo estado guardado: Cliente ${historial[indiceActual].obtenerEstado().nombre}")
    }

    /**
     * Retrocede un paso en el historial si es posible.
     *
     * @return El estado anterior o null si no hay estados anteriores
     */
    fun deshacer(): NMemento? {
        return if (puedeDeshacer()) {
            val mementoAnterior = historial[--indiceActual]
            println("Deshaciendo cambios: Volviendo al estado de ${mementoAnterior.obtenerEstado().nombre}")
            mementoAnterior
        } else {
            println("No hay más estados para deshacer")
            null
        }
    }

    /**
     * Avanza un paso en el historial si es posible.
     *
     * @return El siguiente estado o null si no hay estados posteriores
     */
    fun rehacer(): NMemento? {
        return if (puedeRehacer()) {
            val mementoSiguiente = historial[++indiceActual]
            println("Rehaciendo cambios: Avanzando al estado de ${mementoSiguiente.obtenerEstado().nombre}")
            mementoSiguiente
        } else {
            println("No hay más estados para rehacer")
            null
        }
    }

    // Funciones de utilidad para verificar si se pueden realizar operaciones
    fun puedeDeshacer() = indiceActual > 0
    fun puedeRehacer() = indiceActual < historial.size - 1
}