package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar apoyar un proyecto
 *
 * @author Diego Lesma
 */
public class errorApoyandoProyecto extends IllegalArgumentException {
    /**
     * Constructor de la clase errorApoyandoProyecto
     * @param message mensaje de error
     */
    public errorApoyandoProyecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase errorApoyandoProyecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
