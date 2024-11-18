package com.parcial.apparquip1.Datos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina

class DPlanEjercicio(context: Context) {
    private val dConexion: DConexion = DConexion(context)
    var id: Int = 0
    var idCategoriaEjercicio: Int = 0
    var titulo: String = ""
    var motivo: String = ""
    var objetivo: String = ""
    var video: String = ""
    var proceso: String = ""
    fun insertarPlanEjercicio(): String {
        val dbPlanEjercicio = dConexion.writableDatabase
        val valoresPlanEjercicio = ContentValues().apply {
            put("titulo", titulo)
            put("motivo", motivo)
            put("objetivo", objetivo)
            put("video", video)
            put("proceso", proceso)
        }

        return try {
            val idPlanEjercicio =
                dbPlanEjercicio.insert("planEjercicio", null, valoresPlanEjercicio)
            dbPlanEjercicio.close()

            val dbPlanCategoria = dConexion.writableDatabase
            val valoresPlanCategoria = ContentValues().apply {
                put("id_categoriaEjer", idCategoriaEjercicio)
                put("id_planEjercicio", idPlanEjercicio)
            }

            val idPlanCategoria =
                dbPlanCategoria.insert("plan_categoria", null, valoresPlanCategoria)
            dbPlanCategoria.close()
            if (idPlanCategoria != -1L) {
                "Se insertó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al insertar: ${e.message}"
        }
    }

    fun obtenerPlanesEjercicio(): List<PlanEjercicio> {
        val planesEjercicio = mutableListOf<PlanEjercicio>()
        val db = dConexion.readableDatabase
        val cursor = db.rawQuery(
            """
        SELECT planEjercicio.*, categoria_ejer.nombre AS categoriaNombre
        FROM planEjercicio
        JOIN plan_categoria ON planEjercicio.id = plan_categoria.id_planEjercicio
        JOIN categoria_ejer ON plan_categoria.id_categoriaEjer = categoria_ejer.id
    """, null
        )

        try {
            val idIndex = cursor.getColumnIndex("id")
            val tituloIndex = cursor.getColumnIndex("titulo")
            val categoriaNombreIndex = cursor.getColumnIndex("categoriaNombre")
            val objetivoIndex = cursor.getColumnIndex("objetivo")
            val videoIndex = cursor.getColumnIndex("video")
            val procesoIndex = cursor.getColumnIndex("proceso")

            if (idIndex != -1 && tituloIndex != -1 && categoriaNombreIndex != -1 && objetivoIndex != -1 && videoIndex != -1 && procesoIndex != -1) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val titulo = cursor.getString(tituloIndex)
                    val categoriaNombre = cursor.getString(categoriaNombreIndex)
                    val objetivo = cursor.getString(objetivoIndex)
                    val video = cursor.getString(videoIndex)
                    val proceso = cursor.getString(procesoIndex)
                    planesEjercicio.add(
                        PlanEjercicio(
                            id,
                            titulo,
                            categoriaNombre,
                            objetivo,
                            video,
                            proceso
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
            db.close()
        }

        return planesEjercicio
    }

    fun actualizarPlanEjercicio(): String {
        val db = dConexion.writableDatabase
        val contentValues = ContentValues().apply {
            put("titulo", titulo)
            put("motivo", motivo)
            put("objetivo", objetivo)
            put("video", video)
            put("proceso", proceso)
        }

        return try {
            val resultado = db.update(
                "planEjercicio", contentValues, "id = ?", arrayOf(id.toString())
            )

            // Actualizar la tabla plan_categoria
            val contentValuesPlanCategoria = ContentValues().apply {
                put("id_categoriaEjer", idCategoriaEjercicio)
            }
            val resultadoPlanCategoria = db.update(
                "plan_categoria",
                contentValuesPlanCategoria,
                "id_planEjercicio = ?",
                arrayOf(id.toString())
            )

            db.close()

            if (resultado != -1 && resultadoPlanCategoria != -1) {
                "Se actualizó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al actualizar: ${e.message}"
        }
    }

    fun eliminarPlanEjercicio(): String {
        val db = dConexion.writableDatabase

        // Iniciar la transacción
        db.beginTransaction()
        return try {
            // Primero, eliminar de la tabla plan_categoria
            val resultadoPlanCategoria =
                db.delete("plan_categoria", "id_planEjercicio = ?", arrayOf(id.toString()))

            // Luego, eliminar de la tabla planEjercicio
            val resultadoPlanEjercicio =
                db.delete("planEjercicio", "id = ?", arrayOf(id.toString()))

            // Si ambas eliminaciones fueron exitosas, marcar la transacción como exitosa
            if (resultadoPlanCategoria != -1 && resultadoPlanEjercicio != -1) {
                db.setTransactionSuccessful()
                return "Se eliminó correctamente"
            } else {
                return "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error al eliminar: ${e.message}"
        } finally {
            // Finalizar la transacción
            db.endTransaction()
            db.close()
        }
    }

    fun getRelacionOfRutina(): List<Rutina> {
        val rutinas = mutableListOf<Rutina>()
        val db = dConexion.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "SELECT rutina.* FROM rutina " + "INNER JOIN rutina_ejercicio ON rutina.id = rutina_ejercicio.id_rutina " + "WHERE rutina_ejercicio.id_planEjercicio = ?",
                arrayOf(id.toString())
            )

            val idIndex = cursor.getColumnIndex("id")
            val tituloIndex = cursor.getColumnIndex("titulo")
            val idPlanAlimentacionIndex = cursor.getColumnIndex("id_planAlimentacion")

            if (idIndex != -1 && tituloIndex != -1 && idPlanAlimentacionIndex != -1) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val titulo = cursor.getString(tituloIndex)
                    val idPlanAlimentacion = cursor.getInt(idPlanAlimentacionIndex)
                    rutinas.add(Rutina(id, titulo, idPlanAlimentacion))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return rutinas
    }
}