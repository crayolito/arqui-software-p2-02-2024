package com.parcial.apparquip1.Datos

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio


class DInformeEntrenamiento(context: Context) {
    private val dConexion: DConexion = DConexion(context);
    var idRutina: Int = 0
    fun getPlanAlimentacionPorRutina(): Alimentacion? {
        val db = dConexion.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM alimentacion WHERE id = (SELECT id_planAlimentacion FROM rutina WHERE id = ?)",
            arrayOf(idRutina.toString())
        )

        val idIndex = cursor.getColumnIndex("id")
        val tituloIndex = cursor.getColumnIndex("titulo")
        val descripcionIndex = cursor.getColumnIndex("descripcion")
        val noprocesadoIndex = cursor.getColumnIndex("noprocesado")
        val procesadoIndex = cursor.getColumnIndex("procesado")

        if (idIndex != -1 && tituloIndex != -1 && descripcionIndex != -1 && noprocesadoIndex != -1 && procesadoIndex != -1) {
            if (cursor.moveToFirst()) {
                val alimentacion = Alimentacion(
                    cursor.getInt(idIndex),
                    cursor.getString(tituloIndex),
                    cursor.getString(descripcionIndex),
                    cursor.getString(noprocesadoIndex),
                    cursor.getString(procesadoIndex)
                )
                cursor.close()
                return alimentacion
            }
        }
        cursor.close()
        return null
    }

    fun getPlanesEjerciciosPorRutina(): List<PlanEjercicio> {
        val db = dConexion.readableDatabase
        val cursor = db.rawQuery(
            """
        SELECT planEjercicio.*, categoria_ejer.nombre AS categoriaNombre
        FROM planEjercicio
        JOIN plan_categoria ON planEjercicio.id = plan_categoria.id_planEjercicio
        JOIN categoria_ejer ON plan_categoria.id_categoriaEjer = categoria_ejer.id
        WHERE planEjercicio.id IN (SELECT id_planEjercicio FROM rutina_ejercicio WHERE id_rutina = ?)
    """, arrayOf(idRutina.toString())
        )

        val idIndex = cursor.getColumnIndex("id")
        val tituloIndex = cursor.getColumnIndex("titulo")
        val categoriaNombreIndex = cursor.getColumnIndex("categoriaNombre")
        val objetivoIndex = cursor.getColumnIndex("objetivo")
        val videoIndex = cursor.getColumnIndex("video")
        val procesoIndex = cursor.getColumnIndex("proceso")

        val planesEjercicio = mutableListOf<PlanEjercicio>()
        if (idIndex != -1 && tituloIndex != -1 && categoriaNombreIndex != -1 && objetivoIndex != -1 && videoIndex != -1 && procesoIndex != -1) {
            while (cursor.moveToNext()) {
                val planEjercicio = PlanEjercicio(
                    cursor.getInt(idIndex),
                    cursor.getString(tituloIndex),
                    cursor.getString(categoriaNombreIndex),
                    cursor.getString(objetivoIndex),
                    cursor.getString(videoIndex),
                    cursor.getString(procesoIndex)
                )
                planesEjercicio.add(planEjercicio)
            }
        }
        cursor.close()
        return planesEjercicio
    }
}