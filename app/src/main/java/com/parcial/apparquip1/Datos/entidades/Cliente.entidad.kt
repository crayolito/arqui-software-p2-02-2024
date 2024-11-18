package com.parcial.apparquip1.Datos.entidades

class Cliente {
    var id: Int = 0
    var nombre: String = ""
    var telefono: String = ""
    var email: String = ""
    var meta: String = ""
    var caracteristicas: String = ""
    var altura: Double = 0.0
    var peso: Double = 0.0
    var imc: Double = 0.0
    var edad: Int = 0
    var genero: String = ""
    var ocupacion: String = ""
    var horarioPreferido: String = ""
    var nivelActividad: String = ""
    var experienciaEjercicio: String = ""
    var tipoObjetivo: String = ""
    var restriccionesSalud: String = ""
    var alergias: String = ""
    var necesitaRutinaCasa: Boolean = false
    var extra: String = ""

    constructor(
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
    ) {
        this.id = id
        this.nombre = nombre
        this.telefono = telefono
        this.email = email
        this.meta = meta
        this.caracteristicas = caracteristicas
        this.altura = altura
        this.peso = peso
        this.imc = imc
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

}