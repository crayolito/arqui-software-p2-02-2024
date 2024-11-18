package com.parcial.apparquip1.Presentacion

import NOriginator
import android.content.Context
import com.parcial.apparquip1.Datos.entidades.Cliente
import com.parcial.apparquip1.Negocio.NCaretaker
import com.parcial.apparquip1.Negocio.NCliente
import com.parcial.apparquip1.common.IAFitness
import java.util.Date

class PCliente(context: Context) {
    private val nCliente: NCliente = NCliente(context)
    private val nOriginator = NOriginator()
    private val nCaretaker = NCaretaker()

    // Datos básicos
    var id: Int = 0
    var nombre: String = ""
    var telefono: String = ""
    var email: String = ""
    var meta: String = ""                    // (Pérdida de peso, Ganancia muscular, Tonificación, etc.)
    var caracteristicas: String = ""         // (Alto rendimiento, Rehabilitación, Principiante, etc.)

    // Datos físicos
    var altura: Double = 0.0                 // (1.50 - 2.20 metros)
    var peso: Double = 0.0                   // (40 - 180 kg)
    var imc: Double = 0.0                    // (Bajo peso, Normal, Sobrepeso, etc.)

    // Información personal
    var edad: Int = 0                        // (15 - 80 años)
    var genero: String = ""                  // (Masculino, Femenino, Otro)
    var ocupacion: String = ""               // (Estudiante, Profesional, Deportista, etc.)
    var horarioPreferido: String = ""        // (Mañana, Tarde, Noche)
    var nivelActividad: String = ""          // (Sedentario, Moderado, Activo, Muy activo)
    var experienciaEjercicio: String = ""    // (Principiante, Intermedio, Avanzado)

    // Objetivos y seguimiento
    var tipoObjetivo: String = ""            // (Culturismo, Danza, Influencer, Modelo, etc.)
    var restriccionesSalud: String = ""      // (Lesiones, Enfermedades crónicas, Ninguna)
    var alergias: String = ""                // (Alimentarias, Medicamentos, Ninguna)
    var necesitaRutinaCasa: Boolean = false  // (true: Entrena en casa, false: Entrena en gym)

    // Extra IA Fitness
    var extra : String  = ""
    fun insertarCliente(): String {
        return nCliente.insertarCliente(
            nombre = nombre,
            telefono = telefono,
            email = email,
            meta = meta,
            caracteristicas = caracteristicas,
            altura = altura,
            peso = peso,
            imc = imc,
            edad = edad,
            genero = genero,
            ocupacion = ocupacion,
            horarioPreferido = horarioPreferido,
            nivelActividad = nivelActividad,
            experienciaEjercicio = experienciaEjercicio,
            tipoObjetivo = tipoObjetivo,
            restriccionesSalud = restriccionesSalud,
            alergias = alergias,
            necesitaRutinaCasa = necesitaRutinaCasa,
            extra = extra
        )
    }

    fun obtenerClientes(): List<Cliente> {
        return nCliente.obtenerClientes()
    }

    fun actualizarCliente(): String {
        return nCliente.actualizarCliente(
            id = id,
            nombre = nombre,
            telefono = telefono,
            email = email,
            meta = meta,
            caracteristicas = caracteristicas,
            altura = altura,
            peso = peso,
            imc = imc,
            edad = edad,
            genero = genero,
            ocupacion = ocupacion,
            horarioPreferido = horarioPreferido,
            nivelActividad = nivelActividad,
            experienciaEjercicio = experienciaEjercicio,
            tipoObjetivo = tipoObjetivo,
            restriccionesSalud = restriccionesSalud,
            alergias = alergias,
            necesitaRutinaCasa = necesitaRutinaCasa,
            extra = extra
        )
    }

    fun eliminarCliente(): String {
        return nCliente.eliminarCliente(id)
    }

    suspend fun AIFitness(): String {
        var cliente : Cliente = Cliente(
            id = id,
            nombre = nombre,
            telefono = telefono,
            email = email,
            meta = meta,
            caracteristicas = caracteristicas,
            altura = altura,
            peso = peso,
            imc = imc,
            edad = edad,
            genero = genero,
            ocupacion = ocupacion,
            horarioPreferido = horarioPreferido,
            nivelActividad = nivelActividad,
            experienciaEjercicio = experienciaEjercicio,
            tipoObjetivo = tipoObjetivo,
            restriccionesSalud = restriccionesSalud,
            alergias = alergias,
            necesitaRutinaCasa = necesitaRutinaCasa,
            extra = extra
        );
        val resultado = nCliente.AIFitness(cliente)
        println("resultado ${resultado}");
        return resultado
    }
     fun convertObject(data : String): IAFitness {
        return nCliente.toIAFitness(data);
    }

    fun validarCamposObligatorios(context: Context) {
        nCliente.validarCliente(
            context = context,
            nombre = nombre,
            telefono = telefono,
            email = email,
            meta = meta,
            altura = altura.toString(),
            peso = peso.toString(),
            edad = edad.toString(),
            genero = genero,
            nivelActividad = nivelActividad,
            experienciaEjercicio = experienciaEjercicio,
            tipoObjetivo = tipoObjetivo,
            restriccionesSalud = restriccionesSalud,
        )
    }

    fun guardarEstado() {
        val cliente = Cliente(
            id = id,
            nombre = nombre,
            telefono = telefono,
            email = email,
            meta = meta,
            caracteristicas = caracteristicas,
            altura = altura,
            peso = peso,
            imc = imc,
            edad = edad,
            genero = genero,
            ocupacion = ocupacion,
            horarioPreferido = horarioPreferido,
            nivelActividad = nivelActividad,
            experienciaEjercicio = experienciaEjercicio,
            tipoObjetivo = tipoObjetivo,
            restriccionesSalud = restriccionesSalud,
            alergias = alergias,
            necesitaRutinaCasa = necesitaRutinaCasa,
            extra = extra
        )
        println("Guardando estado - Cliente: ${cliente.nombre}")
        nOriginator.establecerEstado(cliente)
        val memento = nOriginator.guardar()
        nCaretaker.agregarRecuerdo(memento)
    }

    fun deshacer(): Boolean {
        println("Intentando deshacer")
        val memento = nCaretaker.deshacer() ?: return false
        nOriginator.restaurar(memento)
        actualizarDesdeCliente(nOriginator.obtenerEstado())
        println("Estado deshecho - Cliente: ${nombre}")
        return true
    }

    fun rehacer(): Boolean {
        println("Intentando rehacer")
        val memento = nCaretaker.rehacer() ?: return false
        nOriginator.restaurar(memento)
        actualizarDesdeCliente(nOriginator.obtenerEstado())
        println("Estado rehecho - Cliente: ${nombre}")
        return true
    }

    fun actualizarDesdeCliente(cliente: Cliente) {
        id = cliente.id
        nombre = cliente.nombre
        telefono = cliente.telefono
        email = cliente.email
        meta = cliente.meta
        caracteristicas = cliente.caracteristicas
        altura = cliente.altura
        peso = cliente.peso
        imc = cliente.imc
        edad = cliente.edad
        genero = cliente.genero
        ocupacion = cliente.ocupacion
        horarioPreferido = cliente.horarioPreferido
        nivelActividad = cliente.nivelActividad
        experienciaEjercicio = cliente.experienciaEjercicio
        tipoObjetivo = cliente.tipoObjetivo
        restriccionesSalud = cliente.restriccionesSalud
        alergias = cliente.alergias
        necesitaRutinaCasa = cliente.necesitaRutinaCasa
        extra = cliente.extra
    }

    fun puedeDeshacer(): Boolean {
        val puede = nCaretaker.puedeDeshacer()
        println("PCliente - ¿Puede deshacer?: $puede")
        return puede
    }

    fun puedeRehacer(): Boolean {
        val puede = nCaretaker.puedeRehacer()
        println("PCliente - ¿Puede rehacer?: $puede")
        return puede
    }
}