package com.parcial.apparquip1.Datos.entidades


class Alimentacion {
    var id: Int = 0
    var titulo: String = ""
    var descripcion: String = ""
    var noprocesado: String = ""
    var procesado: String = ""

    constructor(
        id: Int, titulo: String, descripcion: String, noprocesado: String, procesado: String
    ) {
        this.id = id
        this.titulo = titulo
        this.descripcion = descripcion
        this.noprocesado = noprocesado
        this.procesado = procesado
    }
}