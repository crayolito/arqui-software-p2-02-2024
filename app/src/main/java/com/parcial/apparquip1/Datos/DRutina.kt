package com.parcial.apparquip1.Datos

import android.content.ContentValues
import android.content.Context
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina

class DRutina(context: Context) {
    private val dConexion: DConexion = DConexion(context)
    var id: Int = 0
    var titulo: String = ""
    var idPlanAlimentacion: Int = 0
    var idPlanesEjercicios: List<PlanEjercicio> = listOf()
    var rutina: Rutina = Rutina(0, "", 0)
    fun obtenerRutina(): Rutina? {
        var rutina: Rutina? = null
        try {
            val db = dConexion.readableDatabase
            val cursor =
                db.rawQuery("SELECT * FROM rutina WHERE id = ?", arrayOf(id.toString()))

            val idIndex = cursor.getColumnIndex("id")
            val tituloIndex = cursor.getColumnIndex("titulo")
            val idPlanAlimentacionIndex = cursor.getColumnIndex("id_planAlimentacion")

            if (idIndex != -1 && tituloIndex != -1 && idPlanAlimentacionIndex != -1) {
                if (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val titulo = cursor.getString(tituloIndex)
                    val idPlanAlimentacion = cursor.getInt(idPlanAlimentacionIndex)
                    rutina = Rutina(id, titulo, idPlanAlimentacion)
                }
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rutina
    }

    fun insertarRutina(): String {
        val dbRutina = dConexion.writableDatabase
        val valoresRutina = ContentValues().apply {
            put("titulo", rutina.titulo)
            put("id_planAlimentacion", idPlanAlimentacion)
        }

        return try {
            val idRutina = dbRutina.insert("rutina", null, valoresRutina)
            dbRutina.close()

            val dbRutinaEjercicio = dConexion.writableDatabase
            dbRutinaEjercicio.beginTransaction()
            for (planEjercicio in idPlanesEjercicios) {
                val valoresRutinaEjercicio = ContentValues().apply {
                    put("id_rutina", idRutina)
                    put("id_planEjercicio", planEjercicio.id)
                }
                val idRutinaEjercicio =
                    dbRutinaEjercicio.insert("rutina_ejercicio", null, valoresRutinaEjercicio)
                println(" ESTE ES EL ID : $idRutinaEjercicio")
            }
            dbRutinaEjercicio.setTransactionSuccessful()
            dbRutinaEjercicio.endTransaction()
            dbRutinaEjercicio.close()
            "Se insertó correctamente"
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al insertar: ${e.message}"
        }
    }

    fun obtenerRutinas(): List<Rutina> {
        val rutinas = mutableListOf<Rutina>()
        try {
            val db = dConexion.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM rutina", null)

            val idIndex = cursor.getColumnIndex("id")
            val tituloIndex = cursor.getColumnIndex("titulo")
            val idPlanAlimentacionIndex = cursor.getColumnIndex("id_planAlimentacion")

            if (idIndex != -1 && tituloIndex != -1) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val titulo = cursor.getString(tituloIndex)
                    val idPlanAlimentacion = cursor.getInt(idPlanAlimentacionIndex)
                    rutinas.add(Rutina(id, titulo, idPlanAlimentacion))
                }
            }


            cursor.close()
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rutinas
    }

    fun actualizarRutina(): String {
        val dbRutina = dConexion.writableDatabase
        val valoresRutina = ContentValues().apply {
            put("titulo", rutina.titulo)
            put("id_planAlimentacion", idPlanAlimentacion)
        }

        return try {
            val resultadoRutina = dbRutina.update("rutina", valoresRutina, "id = ?", arrayOf(rutina.id.toString()))
            dbRutina.close()

            if (resultadoRutina > 0) {
                val dbRutinaEjercicio = dConexion.writableDatabase
                dbRutinaEjercicio.beginTransaction()

                // Primero, elimina las tuplas existentes para esta rutina
                dbRutinaEjercicio.delete("rutina_ejercicio", "id_rutina = ?", arrayOf(rutina.id.toString()))

                // Luego, inserta las nuevas tuplas
                for (planEjercicio in idPlanesEjercicios) {
                    val valoresRutinaEjercicio = ContentValues().apply {
                        put("id_rutina", rutina.id)
                        put("id_planEjercicio", planEjercicio.id)
                    }
                    val idRutinaEjercicio = dbRutinaEjercicio.insert("rutina_ejercicio", null, valoresRutinaEjercicio)
                    println(" ESTE ES EL ID : $idRutinaEjercicio")
                }

                dbRutinaEjercicio.setTransactionSuccessful()
                dbRutinaEjercicio.endTransaction()
                dbRutinaEjercicio.close()

                "Se actualizó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al actualizar: ${e.message}"
        }
    }

    fun eliminarRutina(): String {
        val db = dConexion.writableDatabase
        db.beginTransaction()
        return try {
            val resultadoRutinaEjercicio =
                db.delete("rutina_ejercicio", "id_rutina = ?", arrayOf(id.toString()))

            val resultadoRutina = db.delete("rutina", "id = ?", arrayOf(id.toString()))

            if (resultadoRutinaEjercicio != -1 && resultadoRutina != -1) {
                db.setTransactionSuccessful()
                "Se eliminó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al eliminar: ${e.message}"
        } finally {
            db.endTransaction()
            db.close()
        }
    }
}