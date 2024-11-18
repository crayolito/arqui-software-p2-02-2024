package com.parcial.apparquip1.common

data class OptionPS(
    val imagen: Int,
    val titulo: String,
    val ruta: String
)

data class IAFitness(
    val descripcion: String,
    val ventajas: List<String>,
    val desventajas: List<String>
)



