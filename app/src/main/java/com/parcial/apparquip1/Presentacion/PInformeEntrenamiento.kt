package com.parcial.apparquip1.Presentacion

import android.content.Context
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.Cliente
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.Negocio.NCliente
import com.parcial.apparquip1.Negocio.NInformeEntrenamiento
import com.parcial.apparquip1.Negocio.NRutina


class PInformeEntrenamiento(context: Context) {
    private val nInformeEjercicio: NInformeEntrenamiento = NInformeEntrenamiento(context);
    private val nRutina: NRutina = NRutina(context)
    private val nCliente: NCliente = NCliente(context)

    var idRutina: Int = 0
    var alimentacion: Alimentacion? = null
    var ejercicios: List<PlanEjercicio> = listOf()
    var path: String = ""
    var numeroWhatsapp: String = ""
    fun getPlanAlimentacionPorRutina(): Alimentacion? {
        return nInformeEjercicio.getPlanAlimentacionPorRutina(idRutina)
    }

    fun getPlanesEjerciciosPorRutina(): List<PlanEjercicio> {
        return nInformeEjercicio.getPlanesEjerciciosPorRutina(idRutina)
    }

    fun obtenerRutinas(): List<Rutina>{
        return nRutina.obtenerRutinas()
    }

    fun obtenerClientes(): List<Cliente>{
        return nCliente.obtenerClientes()
    }

    fun generarPdf(
        context: Context
    ): String {
        return nInformeEjercicio.generarPdf(context, alimentacion!!, ejercicios)
    }

    fun abrirPdf(context: Context) {
        nInformeEjercicio.abrirPdf(context, path)
    }

    fun compartirPdfWhatsapp(context: Context) {
        nInformeEjercicio.compartirPdfWhatsapp(context, path, numeroWhatsapp)
    }
}