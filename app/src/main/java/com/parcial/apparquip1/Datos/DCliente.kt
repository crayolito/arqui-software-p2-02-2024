package com.parcial.apparquip1.Datos

import android.content.ContentValues
import android.content.Context
import com.parcial.apparquip1.Datos.entidades.Cliente
import java.util.Date

class DCliente(context: Context) {
    private val dConexion: DConexion = DConexion(context)

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
        val db = dConexion.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", nombre)
            put("telefono", telefono)
            put("email", email)
            put("meta", meta)
            put("caracteristicas", caracteristicas)
            put("altura", altura)
            put("peso", peso)
            put("imc", imc)
            put("edad", edad)
            put("genero", genero)
            put("ocupacion", ocupacion)
            put("horarioPreferido", horarioPreferido)
            put("nivelActividad", nivelActividad)
            put("experienciaEjercicio", experienciaEjercicio)
            put("tipoObjetivo", tipoObjetivo)
            put("restriccionesSalud", restriccionesSalud)
            put("alergias", alergias)
            put("necesitaRutinaCasa", if(necesitaRutinaCasa) 1 else 0)
            put("extra", extra)
        }

        return try {
            val resultado = db.insert("cliente", null, contentValues)
            db.close()

            if (resultado != -1L) {
                "Se insertó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al insertar: ${e.message}"
        }
    }

    fun obtenerClientes(): List<Cliente> {
        val clientes = mutableListOf<Cliente>()
        try {
            val db = dConexion.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM cliente", null)  // Quitamos el ORDER BY para pruebas iniciales

            // Agregar log para verificar si hay registros
            println("Número de registros encontrados: ${cursor.count}")

            if (cursor.moveToFirst()) {  // Verificamos si hay al menos un registro
                do {
                    try {
                        val cliente = Cliente(
                            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")) ?: "",
                            telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")) ?: "",
                            email = cursor.getString(cursor.getColumnIndexOrThrow("email")) ?: "",
                            meta = cursor.getString(cursor.getColumnIndexOrThrow("meta")) ?: "",
                            caracteristicas = cursor.getString(cursor.getColumnIndexOrThrow("caracteristicas")) ?: "",
                            altura = cursor.getDouble(cursor.getColumnIndexOrThrow("altura")),
                            peso = cursor.getDouble(cursor.getColumnIndexOrThrow("peso")),
                            imc = cursor.getDouble(cursor.getColumnIndexOrThrow("imc")),
                            edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                            genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")) ?: "",
                            ocupacion = cursor.getString(cursor.getColumnIndexOrThrow("ocupacion")) ?: "",
                            horarioPreferido = cursor.getString(cursor.getColumnIndexOrThrow("horarioPreferido")) ?: "",
                            nivelActividad = cursor.getString(cursor.getColumnIndexOrThrow("nivelActividad")) ?: "",
                            experienciaEjercicio = cursor.getString(cursor.getColumnIndexOrThrow("experienciaEjercicio")) ?: "",
                            tipoObjetivo = cursor.getString(cursor.getColumnIndexOrThrow("tipoObjetivo")) ?: "",
                            restriccionesSalud = cursor.getString(cursor.getColumnIndexOrThrow("restriccionesSalud")) ?: "",
                            alergias = cursor.getString(cursor.getColumnIndexOrThrow("alergias")) ?: "",
                            necesitaRutinaCasa = cursor.getInt(cursor.getColumnIndexOrThrow("necesitaRutinaCasa")) == 1,
                            extra = cursor.getString(cursor.getColumnIndexOrThrow("extra")) ?: ""
                        )
                        clientes.add(cliente)
                        println("Cliente leído: ${clientes.size}") // Log para verificar cada cliente leído
                    } catch (e: Exception) {
                        println("Error al leer cliente: ${e.message}")
                        e.printStackTrace()
                    }
                } while (cursor.moveToNext())
            } else {
                println("No se encontraron registros en la tabla cliente")
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            println("Error general en obtenerClientes: ${e.message}")
            e.printStackTrace()
        }

        println("Total de clientes cargados: ${clientes.size}")
        return clientes
    }

    fun actualizarCliente(): String {
        return try {
            val db = dConexion.writableDatabase
            val contentValues = ContentValues().apply {
                put("nombre", nombre)
                put("telefono", telefono)
                put("email", email)
                put("meta", meta)
                put("caracteristicas", caracteristicas)
                put("altura", altura)
                put("peso", peso)
                put("imc", imc)
                put("edad", edad)
                put("genero", genero)
                put("ocupacion", ocupacion)
                put("horarioPreferido", horarioPreferido)
                put("nivelActividad", nivelActividad)
                put("experienciaEjercicio", experienciaEjercicio)
                put("tipoObjetivo", tipoObjetivo)
                put("restriccionesSalud", restriccionesSalud)
                put("alergias", alergias)
                put("necesitaRutinaCasa", if(necesitaRutinaCasa) 1 else 0)
                put("extra", extra)
            }
            val resultado = db.update(
                "cliente", contentValues, "id = ?", arrayOf(id.toString())
            )
            db.close()
            if (resultado > 0) {
                "Se actualizó correctamente"
            } else {
                "Ocurrió un error al actualizar"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al actualizar: ${e.message}"
        }
    }

    fun eliminarCliente(id: Int): String {
        println(id)
        return try {
            val db = dConexion.writableDatabase
            val resultado = db.delete("cliente", "id = ?", arrayOf(id.toString()))
            db.close()

            if (resultado > 0) {
                "Se eliminó correctamente"
            } else {
                "Ocurrió un error al eliminar"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al eliminar: ${e.message}"
        }
    }
}