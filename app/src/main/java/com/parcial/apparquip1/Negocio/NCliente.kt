package com.parcial.apparquip1.Negocio

import android.content.Context
import android.widget.Toast
import com.parcial.apparquip1.Datos.entidades.Cliente
import com.parcial.apparquip1.Datos.DCliente
import com.parcial.apparquip1.common.IAFitness
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.json.JSONArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NCliente(context: Context) {
    private val dCliente: DCliente = DCliente(context)

    fun validarCliente(
        context: Context,
        nombre: String,
        telefono: String,
        email: String,
        meta: String,
        altura: String,
        peso: String,
        edad: String,
        genero: String,
        nivelActividad: String,
        experienciaEjercicio: String,
        tipoObjetivo: String,
        restriccionesSalud: String,
    ) {
        // Lista para almacenar campos vacíos
        val camposVacios = mutableListOf<String>()

        // Validar campos obligatorios
        if (nombre.isBlank()) camposVacios.add("Nombre")
        if (telefono.isBlank()) camposVacios.add("Teléfono")
        if (edad.isBlank()) camposVacios.add("Edad")
        if (altura.isBlank()) camposVacios.add("Altura")
        if (peso.isBlank()) camposVacios.add("Peso")
        if (meta.isBlank()) camposVacios.add("Meta")
        if (experienciaEjercicio.isBlank()) camposVacios.add("Experiencia en Ejercicio")
        if (restriccionesSalud.isBlank()) camposVacios.add("Restricciones de Salud")
        if (genero.isBlank()) camposVacios.add("Género")
        if (nivelActividad.isBlank()) camposVacios.add("Nivel de Actividad")
        if (tipoObjetivo.isBlank()) camposVacios.add("Tipo de Objetivo")

        if (camposVacios.isNotEmpty()) {
            Toast.makeText(context,
                "Complete los campos obligatorios: ${camposVacios.joinToString(", ")}",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        try {
            // Validación de altura
            val alturaNum = altura.toDouble()
            if (alturaNum !in 1.50..2.20) {
                Toast.makeText(context,
                    "La altura debe estar entre 1.50 y 2.20 metros",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            // Validación de peso
            val pesoNum = peso.toDouble()
            if (pesoNum !in 40.0..180.0) {
                Toast.makeText(context,
                    "El peso debe estar entre 40 y 180 kg",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            // Validación de edad
            val edadNum = edad.toInt()
            if (edadNum !in 15..80) {
                Toast.makeText(context,
                    "La edad debe estar entre 15 y 80 años",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            // Validación de IMC
            val imc = pesoNum / (alturaNum * alturaNum)
            if (imc !in 15.0..40.0) {
                Toast.makeText(context,
                    "El IMC calculado está fuera de rango normal (${String.format("%.2f", imc)})",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

        } catch (e: NumberFormatException) {
            Toast.makeText(context,
                "Los valores numéricos no tienen el formato correcto",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // Validación de teléfono
        if (!telefono.matches(Regex("^[0-9]{8}$"))) {
            Toast.makeText(context,
                "El teléfono debe tener 8 dígitos numéricos",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // Validación de email si no está vacío
        if (email.isNotEmpty() && !email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            Toast.makeText(context,
                "El formato del email no es válido",
                Toast.LENGTH_LONG
            ).show()
            return
        }
    }

    fun insertarCliente(
        nombre: String,
        telefono: String,
        email: String,
        meta: String,
        caracteristicas: String,
        altura: Double,
        peso: Double,
        imc: Double,
        edad: Int,
        genero: String,
        ocupacion: String,
        horarioPreferido: String,
        nivelActividad: String,
        experienciaEjercicio: String,
        tipoObjetivo: String,
        restriccionesSalud: String,
        alergias: String,
        necesitaRutinaCasa: Boolean,
        extra : String
    ): String {

        dCliente.apply {
            this.nombre = nombre
            this.telefono = telefono
            this.email = email
            this.meta = meta
            this.caracteristicas = caracteristicas
            this.altura = altura
            this.peso = peso
            this.imc = calcularIMC()
            this.edad = edad
            this.genero = genero
            this.ocupacion = ocupacion
            this.horarioPreferido = horarioPreferido
            this.nivelActividad = nivelActividad
            this.experienciaEjercicio = experienciaEjercicio
            this.tipoObjetivo = tipoObjetivo
            this.restriccionesSalud = restriccionesSalud
            this.alergias = alergias
            this.necesitaRutinaCasa = necesitaRutinaCasa
            this.extra = extra
        }

        return dCliente.insertarCliente()
    }

    fun obtenerClientes(): List<Cliente> {
        return dCliente.obtenerClientes()
    }

    fun actualizarCliente(
        id: Int,
        nombre: String,
        telefono: String,
        email: String,
        meta: String,
        caracteristicas: String,
        altura: Double,
        peso: Double,
        imc: Double,
        edad: Int,
        genero: String,
        ocupacion: String,
        horarioPreferido: String,
        nivelActividad: String,
        experienciaEjercicio: String,
        tipoObjetivo: String,
        restriccionesSalud: String,
        alergias: String,
        necesitaRutinaCasa: Boolean,
        extra: String
    ): String {

        dCliente.apply {
            this.id = id
            this.nombre = nombre
            this.telefono = telefono
            this.email = email
            this.meta = meta
            this.caracteristicas = caracteristicas
            this.altura = altura
            this.peso = peso
            this.imc = calcularIMC()
            this.edad = edad
            this.genero = genero
            this.ocupacion = ocupacion
            this.horarioPreferido = horarioPreferido
            this.nivelActividad = nivelActividad
            this.experienciaEjercicio = experienciaEjercicio
            this.tipoObjetivo = tipoObjetivo
            this.restriccionesSalud = restriccionesSalud
            this.alergias = alergias
            this.necesitaRutinaCasa = necesitaRutinaCasa
            this.extra = extra
        }

        return dCliente.actualizarCliente()
    }

    fun eliminarCliente(id: Int): String {
        if (id <= 0) {
            return "ID de cliente no válido"
        }
        return dCliente.eliminarCliente(id)
    }

    fun calcularIMC(): Double {
        return if (dCliente.altura > 0 && dCliente.peso > 0) {
            dCliente.peso / (dCliente.altura * dCliente.altura)
        } else {
            0.0
        }
    }

     fun calcularIMC(altura: Double, peso: Double): Double {
        return if (altura > 0 && peso > 0) {
            peso / (altura * altura)
        } else {
            0.0
        }
    }

    // Función para generar el prompt de la IA basado en un Cliente
    suspend fun AIFitness(cliente: Cliente): String {
            val prompt = """
    Eres un especialista en medicina deportiva y fisiología del ejercicio de alto rendimiento, comunicándote con otro profesional del sector. Tu objetivo es proporcionar un análisis científico avanzado que ayude en la toma de decisiones clínicas y la periodización del entrenamiento.
    
    DATOS DEL CASO:
        Paciente: ${cliente.nombre}
        Datos Antropométricos:
        - Edad: ${cliente.edad} años
        - Género: ${cliente.genero}
        - Masa corporal: ${cliente.peso} kg
        - Talla: ${cliente.altura} m
        - IMC: ${calcularIMC(cliente.altura, cliente.peso)}
        
        Variables Contextuales:
        - Objetivo principal: ${cliente.tipoObjetivo}
        - Nivel de condicionamiento actual: ${cliente.nivelActividad}
        - Background deportivo: ${cliente.experienciaEjercicio}
        - Patologías/Contraindicaciones: ${cliente.restriccionesSalud}
        - Alergias/Intolerancias: ${cliente.alergias}
        - Disponibilidad temporal: ${cliente.horarioPreferido}
        - Requerimientos específicos: Entrenamiento domiciliario - ${cliente.necesitaRutinaCasa}
        - Meta específica: ${cliente.meta}
        - Factores individuales: ${cliente.caracteristicas}
        - Perfil ocupacional: ${cliente.ocupacion}

    REQUERIMIENTO:
    Como especialista, proporciona un análisis clínico exhaustivo que incluya:

    1. En la descripción:
    - Análisis detallado de las vías metabólicas predominantes según el perfil
    - Consideraciones neuromusculares específicas
    - Factores endocrinos relevantes para el caso
    - Análisis biomecánico específico según objetivos y restricciones

    2. En las ventajas:
    - Beneficios fisiológicos específicos basados en evidencia científica
    - Adaptaciones esperadas a nivel celular y sistémico
    - Mejoras previstas en parámetros de rendimiento específicos
    - Optimizaciones metabólicas y hormonales esperadas

    3. En las consideraciones:
    - Umbrales de intensidad y volumen específicos según el perfil
    - Factores de riesgo clínicos a monitorizar
    - Adaptaciones metodológicas necesarias según patologías
    - Parámetros de progresión y periodización recomendados
        
    RETORNA EXACTAMENTE ESTE FORMATO JSON:
    {
        "descripcion": "Consultoría científica avanzada para profesionales",
        "ventajas": [
            "Beneficio fisiológico o de rendimiento avanzado 1",
            "Beneficio fisiológico o de rendimiento avanzado 2",
            ...
        ],
        "consideraciones": [
            "Requisito o consideración científica crítica 1",
            "Requisito o consideración científica crítica 2",
            ...
        ]
    }
    """

        val client = OkHttpClient()

        val requestBody = JSONObject().apply {
            put("model", "gpt-3.5-turbo")
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "system")
                    put("content", prompt)
                })
            })
            put("temperature", 0.7)
            put("max_tokens", 1000)
        }.toString()

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer ")
            .post(requestBody.toRequestBody("application/json".toMediaType()))
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw Exception("Error: ${response.code}")

                val jsonResponse = JSONObject(response.body?.string() ?: "")
                jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
            }
        }
    }

    // Función auxiliar para convertir la respuesta a objeto
    fun toIAFitness(jsonString: String): IAFitness {
        val json = JSONObject(jsonString)
        println("")
        return IAFitness(
            descripcion = json.getString("descripcion"),
            ventajas = json.getJSONArray("ventajas").let { array ->
                List(array.length()) { array.getString(it) }
            },
            desventajas = json.getJSONArray("consideraciones").let { array ->
                List(array.length()) { array.getString(it) }
            }
        )
    }
}