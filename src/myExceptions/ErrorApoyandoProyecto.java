package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar apoyar un proyecto
 *
 * @author Diego Lesma
 */
public class ErrorApoyandoProyecto extends IllegalArgumentException {
    /**
     * Constructor de la clase ErrorApoyandoProyecto
     * @param message mensaje de error
     */
    public ErrorApoyandoProyecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase ErrorApoyandoProyecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
