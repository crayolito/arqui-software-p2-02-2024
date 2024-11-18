package com.parcial.apparquip1.Datos.entidades

class Rutina{
    var id: Int = 0
    var titulo: String = ""
    var idPlanAlimentacion: Int = 0

    constructor(id: Int, titulo: String, idPlanAlimentacion: Int) {
        this.id = id
        this.titulo = titulo
        this.idPlanAlimentacion = idPlanAlimentacion
    }

    public fun obtenerId(): Int {
        return this.id
    }

    public fun obtenerTitulo(): String {
        return this.titulo
    }

    public fun asignarId(id: Int) {
        this.id = id
    }

    public fun asignarTitulo(titulo: String) {
        this.titulo = titulo
    }
}