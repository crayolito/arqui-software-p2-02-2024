import com.parcial.apparquip1.Datos.entidades.Cliente
/**
 * Clase NOriginator (Creador)
 *
 * Esta clase representa el objeto cuyo estado queremos guardar y restaurar.
 * Es responsable de crear los mementos y restaurar su estado a partir de ellos.
 */
class NOriginator {
    // Estado actual del cliente que queremos poder guardar/restaurar
    private var estado: Cliente = Cliente(
        id = 0,
        nombre = "",
        telefono = "",
        email = "",
        meta = "",
        caracteristicas = "",
        altura = 0.0,
        peso = 0.0,
        imc = 0.0,
        edad = 0,
        genero = "",
        ocupacion = "",
        horarioPreferido = "",
        nivelActividad = "",
        experienciaEjercicio = "",
        tipoObjetivo = "",
        restriccionesSalud = "",
        alergias = "",
        necesitaRutinaCasa = false,
        extra = ""
    )

    /**
     * Actualiza el estado actual con nuevos datos del cliente
     *
     * @param nuevoEstado Nuevo estado del cliente a guardar
     */
    fun establecerEstado(nuevoEstado: Cliente) {
        estado = nuevoEstado
    }

    /**
     * Obtiene el estado actual del cliente
     *
     * @return Estado actual del cliente
     */
    fun obtenerEstado() = estado

    /**
     * Crea un nuevo memento con el estado actual
     *
     * @return Nuevo objeto NMemento que contiene una copia del estado actual
     */
    fun guardar(): NMemento {
        return NMemento(estado)
    }

    /**
     * Restaura el estado desde un memento
     *
     * @param recuerdo Memento del cual restaurar el estado
     */
    fun restaurar(recuerdo: NMemento) {
        estado = recuerdo.obtenerEstado()
    }
}

