package com.parcial.apparquip1.Negocio

import android.content.ActivityNotFoundException
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.DInformeEntrenamiento
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.action.PdfAction
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Link
import com.itextpdf.layout.element.Paragraph
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NInformeEntrenamiento(context: Context) {
    private val dInformeEntrenamiento: DInformeEntrenamiento = DInformeEntrenamiento(context)

    fun getPlanAlimentacionPorRutina(idRutina: Int): Alimentacion? {
        if (idRutina <= 0) {
            return null
        }
        dInformeEntrenamiento.idRutina = idRutina
        return dInformeEntrenamiento.getPlanAlimentacionPorRutina()
    }

    fun getPlanesEjerciciosPorRutina(idRutina: Int): List<PlanEjercicio> {
        if (idRutina <= 0) {
            return emptyList()
        }
        dInformeEntrenamiento.idRutina = idRutina
        return dInformeEntrenamiento.getPlanesEjerciciosPorRutina()
    }

    fun generarPdf(
        context: Context, alimentacion: Alimentacion, ejercicios: List<PlanEjercicio>
    ): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = formatter.format(currentDate)
        val pdfName = "Rutina_$formattedDate.pdf"

        // Crear el archivo PDF
        val directory = context.getExternalFilesDir(null)
        val file = File(directory, pdfName)
        val pdfWriter = PdfWriter(file)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        // Definición de colores personalizados
        val Pc_Color = DeviceRgb(0x22, 0x22, 0x22) // Color primario
        val Sc_Color = DeviceRgb(0xFF, 0x6D, 0x1F) // Color secundario

        // Agregar un encabezado
        val title = Paragraph("NEXTLEVELFIT").setFontSize(36f).setFontColor(Sc_Color).setBold()
        document.add(title)

        // Información de alimentación
        document.add(
            Paragraph("Plan de Alimentación:").setFontSize(36f).setFontColor(Sc_Color).setBold()
        )
        document.add(
            Paragraph("Descripción:").setFontSize(18f).setFontColor(Sc_Color).setBold()
        )
        document.add(
            Paragraph(alimentacion.descripcion).setFontSize(14f).setFontColor(Pc_Color)
        )
        document.add(
            Paragraph("Alimentos no procesados:").setFontSize(18f).setFontColor(Sc_Color).setBold()
        )
        document.add(
            Paragraph(alimentacion.noprocesado).setFontSize(14f).setFontColor(Pc_Color)
        )
        document.add(
            Paragraph("Alimentos procesados:").setFontSize(18f).setFontColor(Sc_Color).setBold()
        )
        document.add(
            Paragraph(alimentacion.procesado).setFontSize(14f).setFontColor(Pc_Color)
        )

        document.add(
            Paragraph("Detalles Ejercicios:").setFontSize(36f).setFontColor(Sc_Color).setBold()
        )
        for ((index, ejercicio) in ejercicios.withIndex()) {
            document.add(
                Paragraph("Ejercicio ${index + 1}:").setFontSize(18f).setFontColor(Sc_Color)
                    .setBold()
            )
            document.add(
                Paragraph(ejercicio.titulo).setFontSize(14f).setFontColor(Pc_Color)
            )
            document.add(
                Paragraph("Objetivo:").setFontSize(18f).setFontColor(Sc_Color).setBold()
            )
            document.add(
                Paragraph(ejercicio.objetivo).setFontSize(14f).setFontColor(Pc_Color)
            )
            document.add(
                Paragraph("Proceso:").setFontSize(18f).setFontColor(Sc_Color).setBold()
            )
            document.add(
                Paragraph(ejercicio.proceso).setFontSize(14f).setFontColor(Pc_Color)
            )
            document.add(
                Paragraph("Categoría del Ejercicio:").setFontSize(18f).setFontColor(Sc_Color)
                    .setBold()
            )
            document.add(
                Paragraph(ejercicio.motivo).setFontSize(14f).setFontColor(Pc_Color)
            )

            if (ejercicio.video.isNotEmpty()) {
                val link = Link(
                    "Ver Video Explicativo", PdfAction.createURI(ejercicio.video)
                ).setFontColor(DeviceRgb(0, 0, 139)) // Azul oscuro
                    .setUnderline()
                val paragraph = Paragraph().add(link).setFontSize(14f).setBold()
                document.add(paragraph)
            }

            document.add(Paragraph("\n"))
        }

        // Espacio adicional antes de la nota importante
        document.add(Paragraph("\n"))
        // Nota importante al final del documento
        val notaImportanteColor = DeviceRgb(0xFF, 0x00, 0x00) // Color rojo
        document.add(
            Paragraph("NOTA IMPORTANTE:").setFontSize(18f).setFontColor(notaImportanteColor)
                .setBold()
        )
        document.add(
            Paragraph("Mirarlo los videos de youtube para una mejor comprensión de los ejercicios.").setFontSize(
                14f
            ).setFontColor(notaImportanteColor)
        )

        // Cerrar el documento
        document.close()

        return file.absolutePath
    }

    fun abrirPdf(context: Context, filePath: String?) {
        if (filePath != null) {
            val file = File(filePath)

            // Verificar si el archivo existe
            if (!file.exists()) {
                Toast.makeText(context, "El archivo PDF no existe", Toast.LENGTH_SHORT).show()
                return
            }

            // Obtener URI a través del FileProvider``
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "application/pdf")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Verificar si hay una aplicación disponible para abrir el PDF
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "No hay aplicaciones para abrir PDF", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "La ruta del archivo es nula", Toast.LENGTH_SHORT).show()
        }
    }

    fun compartirPdfWhatsapp(context: Context, filePath: String, numeroWhatsapp: String) {
        val file = File(filePath)
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "application/pdf"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.putExtra("jid", "591$numeroWhatsapp@s.whatsapp.net")
        intent.setPackage("com.whatsapp")

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
        }
    }
}