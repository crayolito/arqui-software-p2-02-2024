package com.parcial.apparquip1.Presentacion

import android.content.Context
import com.itextpdf.kernel.geom.Rectangle
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

class Helpers() {

    fun generarPdf(context: Context): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = formatter.format(currentDate)
        val pdfName = "HolaMundo_$formattedDate.pdf"

        // Crear el archivo PDF
        val directory = context.getExternalFilesDir(null)
        val file = File(directory, pdfName)
        val pdfWriter = PdfWriter(file)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        // Crear un párrafo
        val paragraph = Paragraph("Hola, bienvenido a mi PDF.")
            .setFontSize(14f)

        // Crear un enlace
        val link = Link("Haz clic aquí", PdfAction.createURI("http://www.tutorialspoint.com/"))

        // Agregar el enlace al párrafo
        paragraph.add(link.setUnderline())

        // Agregar el párrafo al documento
        document.add(paragraph)

        // Cerrar el documento
        document.close()

        return file.absolutePath
    }

}