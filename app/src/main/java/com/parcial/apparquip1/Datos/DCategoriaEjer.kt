package com.parcial.apparquip1.Datos

import android.content.ContentValues
import android.content.Context
import com.parcial.apparquip1.Datos.entidades.CategoriaEjer
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio

class DCategoriaEjer(context: Context) {
    private val dConexion: DConexion = DConexion(context)
    var id: Int = 0
    var nombre: String = ""
    var descripcion: String = ""

    fun insertarCategoriaEjer(): String {
        val db = dConexion.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", nombre)
            put("descripcion", descripcion)
        }

        return try {
            val resultado = db.insert("categoria_ejer", null, contentValues)
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

    fun obtenerCategoriasEjer(): List<CategoriaEjer> {
        val categoriasEjer = mutableListOf<CategoriaEjer>()
        try {
            val db = dConexion.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM categoria_ejer", null)

            val idIndex = cursor.getColumnIndex("id")
            val nombreIndex = cursor.getColumnIndex("nombre")
            val descripcionIndex = cursor.getColumnIndex("descripcion")

            if (idIndex != -1 && nombreIndex != -1 && descripcionIndex != -1) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val nombre = cursor.getString(nombreIndex)
                    val descripcion = cursor.getString(descripcionIndex)
                    categoriasEjer.add(CategoriaEjer(id, nombre, descripcion))
                }
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return categoriasEjer
    }

    fun actualizarCategoriaEjer(): String {
        val db = dConexion.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", nombre)
            put("descripcion", descripcion)
        }

        return try {
            val resultado = db.update(
                "categoria_ejer", contentValues, "id = ?", arrayOf(id.toString())
            )
            db.close()

            if (resultado != -1) {
                "Se actualizó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al actualizar: ${e.message}"
        }
    }

    fun eliminarCategoriaEjer(): String {
        val db = dConexion.writableDatabase
        return try {
            val resultado = db.delete("categoria_ejer", "id = ?", arrayOf(id.toString()))
            db.close()

            if (resultado != -1) {
                "Se eliminó correctamente"
            } else {
                "Ocurrió un error"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al eliminar: ${e.message}"
        }
    }

    fun getRelacionOfCategoriaEjer(): List<PlanEjercicio> {
        val planesEjercicio = mutableListOf<PlanEjercicio>()
        val db = dConexion.readableDatabase
        var cursor = db.rawQuery(
            "SELECT planEjercicio.* FROM planEjercicio " +
                    "INNER JOIN plan_categoria ON planEjercicio.id = plan_categoria.id_planEjercicio " +
                    "WHERE plan_categoria.id_categoriaEjer = ?",
            arrayOf(id.toString())
        )

        try {
            val idIndex = cursor.getColumnIndex("id")
            val tituloIndex = cursor.getColumnIndex("titulo")
            val motivoIndex = cursor.getColumnIndex("motivo")
            val objetivoIndex = cursor.getColumnIndex("objetivo")
            val videoIndex = cursor.getColumnIndex("video")
            val procesoIndex = cursor.getColumnIndex("proceso")

            if (idIndex != -1 && tituloIndex != -1 && motivoIndex != -1 && objetivoIndex != -1 && videoIndex != -1 && procesoIndex != -1) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(idIndex)
                    val titulo = cursor.getString(tituloIndex)
                    val motivo = cursor.getString(motivoIndex)
                    val objetivo = cursor.getString(objetivoIndex)
                    val video = cursor.getString(videoIndex)
                    val proceso = cursor.getString(procesoIndex)
                    planesEjercicio.add(PlanEjercicio(id, titulo, motivo, objetivo, video, proceso))
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
}