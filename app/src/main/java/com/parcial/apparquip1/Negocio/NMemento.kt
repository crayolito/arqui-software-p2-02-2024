import com.parcial.apparquip1.Datos.entidades.Cliente
import java.util.Date

/**
 * Clase NMemento
 *
 * Esta clase es responsable de almacenar el estado del Originator en un momento específico.
 * Es inmutable para garantizar la integridad del estado guardado.
 *
 * @property estado El estado del cliente que se está guardando
 */
class NMemento(private val estado: Cliente) {
    /**
     * Obtiene una copia del estado guardado
     *
     * @return Copia del estado del cliente almacenado en este memento
     */
    fun obtenerEstado() = estado
}