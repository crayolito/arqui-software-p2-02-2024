package com.parcial.apparquip1.Datos.entidades

class PlanEjercicio {
    var id: Int = 0
    var titulo: String = ""
    var motivo: String = ""
    var objetivo: String = ""
    var video: String = ""
    var proceso: String = ""

    constructor(
        id: Int,titulo:String, motivo: String, objetivo: String, video: String, proceso: String
    ) {
        this.id = id
        this.titulo = titulo
        this.motivo = motivo
        this.objetivo = objetivo
        this.video = video
        this.proceso = proceso
    }

    public fun obtenerTitulo(): String {
        return this.titulo
    }

    public fun establecerTitulo(titulo: String) {
        this.titulo = titulo
    }

    public fun obtenerId(): Int {
        return this.id
    }

    public fun establecerId(id: Int) {
        this.id = id
    }

    public fun obtenerMotivo(): String {
        return this.motivo
    }

    public fun obtenerObjetivo(): String {
        return this.objetivo
    }

    public fun obtenerVideo(): String {
        return this.video
    }

    public fun obtenerProceso(): String {
        return this.proceso
    }

    public fun establecerMotivo(motivo: String) {
        this.motivo = motivo
    }

    public fun establecerObjetivo(objetivo: String) {
        this.objetivo = objetivo
    }

    public fun establecerVideo(video: String) {
        this.video = video
    }

    public fun establecerProceso(proceso: String) {
        this.proceso = proceso
    }
}